#encoding: utf-8

#noyard
module GamePlay
  class StorageItems < Base
    DON = "Donner"
    RET = "Retirer"
    INF = "Résumé"
    QTT = "Quitter"
    def initialize
      @utils = StorageUtils.new
      @index = 1
      @utils.draw_selector(@index)
      @running = true
    end

    def update
      @utils.update
      return if $game_temp.message_text
      if Input.trigger?(:B)
        c = @utils.display_message(_get(33, 85), 2, _get(33, 83), _get(33, 84))
        @running = false if (c == 1)
      end
      if (@index == 0) # Changement de boîte
        @index = @utils.changer_boite(@index)
      elsif (@index > 0 and @index < 31)# Déplacement dans la boîte
        @index = @utils.deplacement_boite(@index)
        if (Input.trigger?(:A))
          return if (!$storage.isPokemon?(@index - 1))
          choice
        end
      else # Déplacement dans l'équipe
        @index = @utils.deplacement_equipe(@index)
        if (Input.trigger?(:A))
          return if ($actors[@index - 31] == nil)
          choice
        end
      end
    end

    def choice
      arr = Array.new
      arr.push(_get(33, 80), _get(33, 38), _get(33, 41), _get(33, 82))
      ind = @utils._party_window(*arr)
      if (ind == 0)
        donner_objet
      elsif (ind == 1)
        retirer_objet
      elsif (ind == 2)
        @utils.sumary_pokemon(@index)
      end
    end

    def donner_objet
      if (@index > 0 and @index < 31)

      else

      end
    end

    def retirer_objet
      if (@index > 0 and @index < 31)

      else
        pokemon = $actors[@index - 31]
        if (pokemon.item_hold == 0)
          @utils.display_message(format(_ext(9000, 91), pokemon.given_name), 1)
        else

        end
      end
    end

    def dispose
      @utils.dispose
    end
  end
end

