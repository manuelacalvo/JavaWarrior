module UI
  # UI displaying the Reminding move list
  class Summary_Remind < Summary_Skills
    # @return [Integer] maximum number of moves shown in the screen
    MAX_MOVES = 5
    # Create a new Summary_Remind UI for the summary
    # @param viewport [Viewport]
    # @param pokemon [PFM::Pokemon] Pokemon that should relearn some skills
    def initialize(viewport, pokemon)
      # @type [Integer] offset index telling the first move in the list that is shown on the screen
      @offset_index = 0
      super(viewport)
      # Moving the original interface
      set_position(0, 71)
      @move_info.set_position(0, 71)
      @y = 0
      # Fixing the background position
      @stack.first.set_position(0, 0)
      # Add the pokemon info
      init_pokemon_info
      # Setting the pokemon info
      self.data = pokemon
    end

    # Set the Pokemon shown
    # @param pokemon [PFM::Pokemon]
    def data=(pokemon)
      super
      @gender.ox = 88 - @name.real_width
      @nature_text.text = PFM::Text.parse(28, pokemon.nature_id)
      # Load the stat color according to the nature
      nature = pokemon.nature
      1.upto(5) do |i|
        color = nature[i] < 100 ? 23 : 22
        color = 0 if nature[i] == 100
        @stat_name_texts[i - 1].load_color(color)
      end
    end

    # Set the index of the shown move
    # @param index [Integer]
    def index=(index)
      last_index = (@index || 0) - @offset_index
      index = fix_index(index)
      @skills[last_index].selected = false
      @index = index.to_i
      @move_info.data = @learnable_skills[@index] if @learnable_skills
      @skills[@index - @offset_index].selected = true
    end

    private

    # Return the background name
    # @return [String]
    def background_name
      'summary/remind'
    end

    # Init the skills of the UI
    def init_skills
      @skills = Array.new(5) { |index| Remind_Skill.new(@viewport, index) }
    end

    # Init the Pokemon Info
    def init_pokemon_info
      @name = add_text(11, 8, 100, 16, :given_name, type: SymText, color: 9)
      @gender = push(101, 10, nil, type: GenderSprite)
      @level_text = add_text(11, 8 + 14, 60, 16, _get(27, 29), color: 9) # Level
      add_text(14 + @level_text.real_width, 8 + 14, 95, 16, :level_text, type: SymText, color: 11)
      push(94, 20, nil, type: PokemonIconSprite)
      init_stats
    end

    # Init the stat texts
    def init_stats
      texts = _get_file(27)
      @nature_text = add_text(114, 19, 60, 16, '') # Nature
      @stat_name_texts = []
      add_text(114, 19 + 16, 60, 16, texts[15]) # HP
      @stat_name_texts << add_text(114, 19 + 32, 60, 16, texts[18]) # Attack
      @stat_name_texts << add_text(114, 19 + 48, 60, 16, texts[20]) # Defense
      @stat_name_texts << add_text(114 + 97, 19 + 16, 120, 16, texts[26]) # Speed
      @stat_name_texts << add_text(114 + 97, 19 + 32, 120, 16, texts[22]) # Attack Spe
      @stat_name_texts << add_text(114 + 97, 19 + 48, 95, 16, texts[24]) # Defense Spe
      # --- Data part ---
      add_text(114, 19 + 16, 95, 16, :hp_text, 2, type: SymText, color: 1)
      add_text(114, 19 + 32, 95, 16, :atk_basis, 2, type: SymText, color: 1)
      add_text(114, 19 + 48, 95, 16, :dfe_basis, 2, type: SymText, color: 1)
      add_text(114 + 97, 19 + 16, 95, 16, :spd_basis, 2, type: SymText, color: 1)
      add_text(114 + 97, 19 + 32, 95, 16, :ats_basis, 2, type: SymText, color: 1)
      add_text(114 + 97, 19 + 48, 95, 16, :dfs_basis, 2, type: SymText, color: 1)
    end

    # Update the skills shown in the UI
    # @param pokemon [PFM::Pokemon]
    def update_skills(pokemon)
      @learnable_skills = pokemon.remindable_skills
      @move_info.data = @learnable_skills[@index]
      update_skill_list
    end

    # Update the skill list
    def update_skill_list
      @skills.each_with_index do |skill_stack, index|
        skill_stack.data = @learnable_skills[index + @offset_index]
      end
    end

    # Fix the index value
    # @param index [Integer] requested index
    # @return [Integer] fixed index
    def fix_index(index)
      max_index = (@learnable_skills&.size || 1) - 1
      if index > max_index
        index = 0
      elsif index < 0
        index = max_index
      end
      fix_offset_index(index, max_index)
      return index
    end

    # Fix the @offset_index value
    # @param index [Integer] fixed index
    # @param max_index [Integer] last possible index
    def fix_offset_index(index, max_index)
      last_offset_index = @offset_index
      mid_index = MAX_MOVES / 2
      if index > mid_index
        if index + mid_index < max_index
          @offset_index = index - mid_index
        else
          @offset_index = max_index - mid_index * 2
        end
      else
        @offset_index = 0
      end
      update_skill_list if last_offset_index != @offset_index
    end
  end

  # UI part displaying a Skill in the Summary_Remind UI
  class Remind_Skill < Summary_Skill
    # Create a new skill
    # @param viewport [Viewport]
    # @param index [Integer] index of the skill in the UI
    def initialize(viewport, index)
      super
      set_position(12, 42 + index * 32)
      @selector.x += 6
      @stack[1].y += 15
      @stack[2].x -= 32
      @stack[4].width = 60
    end

    private

    # Return the name of the selector file
    # @return [String]
    def selector_name
      'summary/remind_selector'
    end

    # Return the name of the method used to get the PP text
    # @return [Symbol]
    def pp_method
      :ppmax
    end
  end
end
