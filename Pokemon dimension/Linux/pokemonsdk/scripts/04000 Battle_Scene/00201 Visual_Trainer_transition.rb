module Battle
  class Visual
    # Method that show the pre_transition of the battle
    def show_pre_transition
      @transition = battle_transition.new(@battle_scene, @screenshot, @viewport)
      @animations << @transition
      @transition.pre_transition
      @locking = true
    end

    # Method that show the trainer transition of the battle
    def show_transition
      # Load transtion (x/y, dpp, frlg)
      # store the transition loop
      # Show the message "issuing a battle"
      # store the enemy ball animation
      # Show the message "send x & y"
      # store the actor ball animation
      # show the message "send x & y"
    end

    # Function storing a battler sprite in the battler Hash
    # @param bank [Integer] bank where the battler should be
    # @param position [Integer, Symbol] Position of the battler
    # @param sprite [Sprite] battler sprite to set
    def store_battler_sprite(bank, position, sprite)
      @battlers[bank][position] = sprite
    end

    private

    # Return the current battle transition
    # @return [Class]
    def battle_transition
      collection = $game_temp.trainer_battle ? TRAINER_TRANSITIONS : WILD_TRANSITIONS
      collection[$game_variables[Yuki::Var::Trainer_Battle_ID]]
    end

    # List of Wild Transitions
    # @return [Hash{ Integer => Class }]
    WILD_TRANSITIONS = {}

    # List of Trainer Transitions
    # @return [Hash{ Integer => Class }]
    TRAINER_TRANSITIONS = {}
  end
end
