#encoding: utf-8

module PFM
  # The Pokedex informations
  #
  # The main Pokedex object is stored in $pokedex or $pokemon_party.pokedex
  # @author Nuri Yuri
  class Pokedex
    # Create a new Pokedex object
    def initialize
      @seen = 0
      @captured = 0
      @has_seen_and_forms = Array.new($game_data_pokemon.size, 0)
      @has_captured = Array.new($game_data_pokemon.size, false)
      @nb_fought = Array.new($game_data_pokemon.size, 0)
      @nb_captured = Array.new($game_data_pokemon.size, 0)
    end

    # Enable the Pokedex
    def enable
      $game_switches[Yuki::Sw::Pokedex] = true
    end

    # Test if the Pokedex is enabled
    # @return [Boolean]
    def enabled?
      $game_switches[Yuki::Sw::Pokedex]
    end

    # Disable the Pokedex
    def disable
      $game_switches[Yuki::Sw::Pokedex] = false
    end

    # Set the national flag of the Pokedex
    # @param mode [Boolean] the flag
    def set_national(mode)
      $game_switches[Yuki::Sw::Pokedex_Nat] = (mode == true)
    end

    # Is the Pokedex showing national Pokemon
    # @return [Boolean]
    def national?
      return $game_switches[Yuki::Sw::Pokedex_Nat]
    end

    # Return the number of Pokemon seen
    # @return [Integer]
    def pokemon_seen
      return @seen
    end

    # Return the number of captured Pokemon
    # @return [Integer]
    def pokemon_captured
      return @captured
    end

    # Return the number of Pokemon captured by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @return [Integer]
    def pokemon_captured_count(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return @nb_captured[id].to_i
    end

    # Change the number of Pokemon captured by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @param number [Integer] the new number
    def pokemon_captured_set_count(id, number)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      @nb_captured[id] = number.to_i
    end

    # Increase the number of pokemon captured by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    def pokemon_captured_inc(id)
      return unless enabled?
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      @nb_captured[id] = @nb_captured[id].to_i.next
    end

    # Return the number of Pokemon fought by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @return [Integer]
    def pokemon_fought(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return @nb_fought[id].to_i
    end

    # Change the number of Pokemon fought by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @param number [Integer] the number of Pokemon fought in the specified specie
    def pokemon_mark_fought(id, number)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      @nb_fought[id] = number.to_i
    end

    # Increase the number of Pokemon fought by specie
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    def pokemon_fought_inc(id)
      return unless enabled?
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      @nb_fought[id] = @nb_fought[id].to_i.next
    end

    # Mark a pokemon as seen
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @param form [Integer] the specific form of the Pokemon
    def mark_seen(id, form = 0)
      return unless enabled?
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      @seen += 1 if @has_seen_and_forms[id].to_i == 0
      @has_seen_and_forms[id] = @has_seen_and_forms[id].to_i | (1 << form)
      $game_variables[Yuki::Var::Pokedex_Seen] = @seen
    end

    # Unmark a pokemon as seen
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @param form [Integer, false] if false, all form will be unseen, otherwise the specific form will be unseen
    def unmark_seen(id, form = false)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      if form
        @has_seen_and_forms[id] = @has_seen_and_forms[id].to_i & ~(1 << form)
      else
        @has_seen_and_forms[id] = 0
      end
      @seen -= 1 if @has_seen_and_forms[id] == 0
      $game_variables[Yuki::Var::Pokedex_Seen] = @seen
    end

    # Mark a Pokemon as captured
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    def mark_captured(id)
      return unless enabled?
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      unless @has_captured[id]
        @has_captured[id] = true
        @captured += 1
      end
      $game_variables[Yuki::Var::Pokedex_Catch] = @captured
    end

    # Unmark a Pokemon as captured
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    def unmark_captured(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return if id >= $game_data_pokemon.size
      if @has_captured[id]
        @has_captured[id] = false
        @captured -= 1
      end
      $game_variables[Yuki::Var::Pokedex_Catch] = @captured
    end

    # Has the player seen a Pokemon
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @return [Boolean]
    def has_seen?(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return false if id >= $game_data_pokemon.size
      return @has_seen_and_forms[id].to_i != 0
    end

    # Has the player captured a Pokemon
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @return [Boolean]
    def has_captured?(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return false if id >= $game_data_pokemon.size
      return @has_captured[id]
    end

    # Get the seen forms informations of a Pokemon
    # @param id [Integer, Symbol] the id of the Pokemon in the database
    # @return [Integer]
    def get_forms(id)
      id = GameData::Pokemon.get_id(id) if id.is_a?(Symbol)
      return 0 if id >= $game_data_pokemon.size
      return @has_seen_and_forms[id].to_i
    end

    # Calibrate the Pokedex information (seen/captured)
    def calibrate
      @seen = 0
      @captured = 0
      1.step($game_data_pokemon.size - 1) do |id|
        @seen += 1 if @has_seen_and_forms[id].to_i != 0
        @captured += 1 if @has_captured[id]
      end
      $game_variables[Yuki::Var::Pokedex_Catch] = @captured
      $game_variables[Yuki::Var::Pokedex_Seen] = @seen
    end
  end
end
