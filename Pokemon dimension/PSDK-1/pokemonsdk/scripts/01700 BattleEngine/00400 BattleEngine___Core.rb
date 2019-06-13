#encoding: utf-8

#noyard
module BattleEngine
  #===
  #>Récupération des contantes utiles
  #===
  ::PFM::Text.define_const(self)

=begin
  Différents états
  :pp => nombre de pp que l'attaque devrait faire perdre (ceci sera modifié par certaines attaques)
=end
  #===
  #>Variable contenant les Pokémon présent sur le banc des alliés
  #===
  @_Actors=[]
  #===
  #>Variable contenant les Pokémons présents sur le banc des ennemis
  #===
  @_Enemies=[]
  #===
	#>Variable contenant les messages
	#===
	@message_stack=[]
	#===
  #>Variable contenant l'index d'attaque correspondant au premier attaquant
  #===
  @_AttackFirst = 0
  #===
  #>Variable contenant l'index d'attaque correspondant au dernier attaquant
  #===
  @_AttackLast = 1
  #===
  #>Variable contenant les actions réalisés lors round
  #===
  @_Actions = []

  #>A partir d'ici on défini des singleton method
  module_function
  #===
  #>use_skill
  #  Méthode permettant d'executer une attaque, les resultats des calculs seront intégrés au stack
  #---
  #E : <BE_Model1>
  #    display_atk : true/false   Indique si l'attaque vient d'être lancée (quand ça a plusieurs cibles)
  #S : Aucune
  #V : 
  #===
  def use_skill(launcher, target, skill, display_atk=true)
    #===
    #> Indication des données pour l'interpreter
    #===
    _message_stack_push([:parametre, launcher, (target ? target : launcher), skill])
    #===
    #> Traitement du cas où l'attaque vient tout juste d'être lancée
    #===
    if display_atk
      @_State[:pp] = 1
      @_State[:ext_info] = nil
      target = Abilities.target_attrating(launcher, target, skill) if skill.is_one_target?
      target = _follow_me_check(launcher, target, skill) #> Par Ici / Poudre Fureur
    else
      @_State[:pp] = 0
    end
    _State_local_update(launcher, target)
    #===
    #> Appel de la fonction de l'attaque
    #===
    if BattleEngine.private_method_defined?(skill.symbol)
      BattleEngine.send(skill.symbol, launcher, target, skill)
#      _message_stack_push([:no_more_msg])
    else
      s_basic(launcher, target, skill)
#      _message_stack_push([:no_more_msg])
    end

    #>Fin de la méthode
    #>Perte des PP
    if(@_State[:pp]>0)
      skill.pp-=@_State[:pp]
      skill.pp-=1 if Abilities::enemy_has_ability_usable(launcher, 72) #> Pression
      if(skill.pp <= 0 and launcher.battle_effect.has_encore_effect? and !@IA_flag)
        launcher.battle_effect.apply_encore(nil)
      elsif(skill.pp <= 0 and @_State[:launcher_item] == 154) #> Baie Mepo
        _mp([:berry_use, launcher, true])
        _mp([:set_pp, 10])
        _msgp(19, 917, launcher, ITEM2[1] => launcher.item_name, MOVE[2] => skill.name)
      end

      #> Enregistrement de l'attaque (pour photocopie)
      @_State[:last_skill] = skill if skill.id != 383
    end
    #>Colérique
    _mp([:change_atk, target, 2]) if(@_State[:last_critical_hit] > 1 and @_State[:target_ability] == 31)
    #>Suppression du verrouillage
    _mp([:apply_effect, launcher, :apply_lock_on, nil]) if skill.id != 199 and launcher.battle_effect.has_lock_on_effect?
  end

  #===
  #>Récupération de la variable state
  #===
  def state
    @_State
  end
  #===
  #>_State_decrease
  # Réduit le compteur d'un status + annonce du message (fichier 18)
  #===
  def _State_decrease(symbol, text_id)
    if @_State[symbol] > 0
      _mp([:msgf, _parse(18, text_id)]) if((@_State[symbol] -= 1) <= 0)
    end
  end
  #===
  #>_State_remove
  # Remet à 0 ou false l'état d'un status du combat
  #===
  def _State_remove(symbol, text_id)
    value = @_State[symbol]
    if value.is_a?(Integer) and value > 0
      @_State[symbol] = 0
      _mp([:msgf, _parse(18, text_id)])
    elsif value == true
      @_State[symbol] = false
      _mp([:msgf, _parse(18, text_id)])
    end
  end
  #===
  #>_State_update
  # Mise à jour de la variable state
  #===
  def _State_update
    st = @_State
    @_State[:act_follow_me] = nil
    @_State[:enn_follow_me] = nil
    _State_decrease(:water_sport, 119)
    _State_decrease(:mud_sport, 121)
    _State_decrease(:trick_room, 122)
    _State_decrease(:gravity, 124)
    _State_decrease(:act_reflect, 132)
    _State_decrease(:enn_reflect, 133)
    _State_decrease(:act_light_screen, 136)
    _State_decrease(:enn_light_screen, 137)
    _State_decrease(:act_safe_guard, 140)
    _State_decrease(:enn_safe_guard, 141)
    _State_decrease(:act_mist, 144)
    _State_decrease(:enn_mist, 145)
    _State_decrease(:act_tailwind, 148)
    _State_decrease(:enn_tailwind, 149)
    _State_decrease(:act_lucky_chant, 152)
    _State_decrease(:enn_lucky_chant, 153)
    _State_decrease(:act_rainbow, 172)
    _State_decrease(:enn_rainbow, 173)
    _State_decrease(:act_firesea, 176)
    _State_decrease(:enn_firesea, 177)
    _State_decrease(:act_swamp, 180)
    _State_decrease(:enn_swamp, 181)
    _State_decrease(:wonder_room, 185)
    _State_decrease(:magic_room, 187)
  end
  #===
  #>_State_sub_update
  # Mise à jour à chaque attaque (ou KO)
  #===
  def _State_sub_update
    st = @_State
    st[:klutz] = false
    st[:air_lock] = false
    get_battlers.each do |i|
      if(i and !i.dead? and i.position)
        if(Abilities.has_ability_usable(i, 116)) #>Maladresse
          st[:klutz] = i
        elsif(Abilities.has_abilities(i, 109, 29)) #> Air Lock ou Ciel Gris
          st[:air_lock] = i
        end
      end
    end
  end
  #===
  #>_State_reset
  # Remise à zéro de la variable state
  #===
  def _State_reset
    @_State = {
    :last_critical_hit => 1, 
    :trick_room => 0, #> Compteur de Distortion
    :klutz => false, #> Etat de maladresse
    :air_lock => false, #> état de Air lock et ciel gris
    :last_type_modifier => 1,
    :act_light_screen => 0, #>Mur lumière
    :enn_light_screen => 0,
    :act_reflect => 0, #>Protection
    :enn_reflect => 0,
    :act_tailwind => 0, #>Vent arrière
    :enn_tailwind => 0,
    :act_lucky_chant => 0, #>Air Veinard
    :enn_lucky_chant => 0,
    :gravity => 0, #>Compteur de gravité
    :water_sport => 0, #>Tourniquet
    :mud_sport => 0, #>Lance-Boue
    :last_skill => nil, #>Dernière attaque (pour Photocopie)
    :knock_off => [], #>Sabotage
    :magic_room => 0, #> Zone Magique
    :wonder_room => 0, #> Zone Étrange
    :act_follow_me => nil, #> Par Ici / Poudre Fureur Actors
    :enn_follow_me => nil, #> Par Ici / Poudre Fureur Ennemis
    :act_spikes => 0, #> Picots coté actors
    :enn_spikes => 0, #> Picots coté ennemis
    :act_toxic_spikes => 0, #> Picots toxiques coté actors
    :enn_toxic_spikes => 0, #> Picots toxiques coté ennemis
    :act_stealth_rock => false, #> Piège de rock act
    :enn_stealth_rock => false, #> Piège de rock enn
    :act_sticky_web => false, #> Toile gluante act
    :enn_sticky_web => false, #> Toile gluante enn
    :act_safe_guard => 0, #> Rune protect
    :enn_safe_guard => 0, #> Rune protect
    :act_mist => 0, #> Rune protect
    :enn_mist => 0, #> Rune protect

    :act_rainbow => 0, #> Double la chance de status (A-eau-feu)
    :enn_rainbow => 0, #> Double la chance de status
    :act_swamp => 0, #> Réduit la vitesse par 2 (A-eau-herbe)
    :enn_swamp => 0, #> Réduit la vitesse par 2
    :act_firesea => 0, #> Inflige des dégats tous les tours (1/8)
    :enn_firesea => 0, #> Inflige des dégats tous les tours (1/8)

    :launcher_item => 0, #> Objet porté par le lanceur
    :target_item => 0, #>Objet porté par la cible
    :launcher_ability => 0, #>Talent du lanceur
    :target_ability => 0, #>Talent de la cible
    :ext_info => nil, #> Informations supplémentaires pour les attaques
    :none => nil}
  end
  _State_reset

  #===
  #>Mise à jour des infos relative au lanceur et à la cible
  #===
  def _State_local_update(launcher, target)
    st = @_State
    st[:launcher_item] = _has_item(launcher, launcher.battle_item) ? launcher.battle_item : 0
    st[:target_item] = _has_item(target, target.battle_item) ? target.battle_item : 0
    st[:launcher_ability] = Abilities.has_ability_usable(launcher, launcher.ability) ? -1 : launcher.ability
    st[:target_ability] = Abilities.has_ability_usable(target, target.ability) ? -1 : target.ability
  end
  #===
  #>Gestion de l'IA
  #===
  def _IA?
    return @IA_flag
  end

  def _enable_ia
    @IA_flag = true
    _init_ia_state
  end

  def _init_ia_state
    unless @_OriginalState
      @_OriginalState = @_State
      @_OriginalEnemies = @_Enemies
      @_OriginalActors = @_Actors
    end
  end

  def _load_ia_state
    @message_stack.clear
    @_State, @_Enemies, @_Actors = Marshal.load(Marshal.dump([@_OriginalState, @_OriginalEnemies, @_OriginalActors]))
  end

  def _disable_ia
    @IA_flag = false
    @_State = @_OriginalState
    @_Enemies = @_OriginalEnemies
    @_Actors = @_OriginalActors
    @_OriginalState = nil
    @_OriginalEnemies = nil
    @_OriginalActors = nil
    @message_stack.clear
  end
end
