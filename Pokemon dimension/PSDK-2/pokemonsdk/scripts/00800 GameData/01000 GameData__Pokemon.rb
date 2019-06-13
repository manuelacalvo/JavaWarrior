#encoding: utf-8

module GameData
  # Pokemon Data Structure
  # @author Nuri Yuri
  class Pokemon < Base
    # Height of the Pokemon in metter
    # @return [Numeric]
    attr_accessor :height
    # Weight of the Pokemon in Kg
    # @return [Numeric]
    attr_accessor :weight
    # Regional id of the Pokemon
    # @return [Integer]
    attr_accessor :id_bis
    # First type of the Pokemon
    # @return [Integer]
    attr_accessor :type1
    # Second type of the Pokemon
    # @return [Integer]
    attr_accessor :type2
    # HP statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_hp
    # ATK statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_atk
    # DFE statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_dfe
    # SPD statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_spd
    # ATS statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_ats
    # DFS statistic of the Pokemon
    # @return [Integer]
    attr_accessor :base_dfs
    # HP EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_hp
    # ATK EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_atk
    # DFE EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_dfe
    # SPD EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_spd
    # ATS EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_ats
    # DFS EVs givent by the Pokemon when defeated
    # @return [Integer]
    attr_accessor :ev_dfs
    # List of moves the Pokemon can learn by level.
    #   List formated like this : level_move1, id_move1, level_move2, id_move2, ...
    # @return [Array<Integer, Integer>]
    attr_accessor :move_set
    # List of moves (id in the database) the Pokemon can learn by using HM and TM
    # @return [Array<Integer>]
    attr_accessor :tech_set
    # Level when the Pokemon can naturally evolve
    # @return [Integer, nil]
    attr_accessor :evolution_level
    # ID of the Pokemon after its evolution
    # @return [Integer] 0 = No evolution
    attr_accessor :evolution_id
    # Special evolution informations
    # @return [Hash, nil]
    attr_accessor :special_evolution
    # Index of the Pokemon exp curve (GameData::EXP_TABLE)
    # @return [Integer]
    attr_accessor :exp_type
    # Base experience the Pokemon give when defeated (used in the exp caculation)
    # @return [Integer]
    attr_accessor :base_exp
    # Loyalty the Pokemon has at the begining
    # @return [Integer]
    attr_accessor :base_loyalty
    # Factor used during the catch_rate calculation
    # @return [Integer] 0 = Uncatchable (even with Master Ball)
    attr_accessor :rareness
    # Chance in % the Pokemon has to be a female, if -1 it'll have no gender.
    # @return [Integer]
    attr_accessor :female_rate
    # The two groupes of compatibility for breeding. If it includes 15, there's no compatibility.
    # @return [Array(Integer, Integer)]
    attr_accessor :breed_groupes
    # List of move ID the Pokemon can have after hatching if one of its parent has the move
    # @return [Array<Integer>]
    attr_accessor :breed_moves
    # Number of step before the egg hatch
    # @return [Integer]
    attr_accessor :hatch_step
    # List of items with change (in percent) the Pokemon can have when generated
    #   List formated like this : [id item1, chance item1, id item2, chance item2, ...]
    # @return [Array<Integer, Integer>]
    attr_accessor :items
    # ID of the baby the Pokemon can have while breeding
    # @return [Integer] 0 = no baby
    attr_accessor :baby
    # Current form of the Pokemon
    # @return [Integer] 0 = common form
    attr_accessor :form
    # List of ability id the Pokemon can have [common, rare, ultra rare]
    # @return [Array(Integer, Integer, Integer)]
    attr_accessor :abilities
    # List of moves the Pokemon can learn from a NPC
    # @return [Array<Integer>]
    attr_accessor :master_moves
    # Safely return the name of the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @return [String]
    def self.name(id)
      return GameData::Text.get(0,id)
    end
    # Safely return the description of the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @return [String]
    def self.descr(id)
      return GameData::Text.get(2,id)
    end
    # Safely return the species of the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @return [String]
    def self.species(id)
      return GameData::Text.get(1,id)
    end
    # Safely return the data of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [GameData::Pokemon]
    def self.get_data(id, form)
      return $game_data_pokemon[0][0] unless data = $game_data_pokemon[id]
      return data[0] unless data = data[form]
      return data
    end
    # Create a new GameData::Pokemon object
    def initialize(height, weight, id_bis, type1, type2, base_hp, base_atk, 
      base_dfe, base_spd, base_ats, base_dfs, ev_hp, ev_atk, ev_dfe, ev_spd, 
      ev_ats, ev_dfs, move_set, tech_set, evolution_level, evolution_id, 
      special_evolution, exp_type, base_exp, base_loyalty, rareness, 
      female_rate, abilities, breed_groupes, breed_moves, master_moves, 
      hatch_step, items, baby)
      @height = height
      @weight = weight
      @id_bis = id_bis
      @type1 = type1
      @type2 = type2
      @base_hp = base_hp
      @base_atk = base_atk
      @base_dfe = base_dfe
      @base_spd = base_spd
      @base_ats = base_ats
      @base_dfs = base_dfs
      @ev_hp = ev_hp
      @ev_atk = ev_atk
      @ev_dfe = ev_dfe
      @ev_spd = ev_spd
      @ev_ats = ev_ats
      @ev_dfs = ev_dfs
      @move_set = move_set
      @tech_set = tech_set
      @evolution_level = evolution_level
      @evolution_id = evolution_id
      @special_evolution = special_evolution
      @exp_type = exp_type
      @base_exp = base_exp
      @base_loyalty = base_loyalty
      @rareness = rareness
      @female_rate = female_rate
      @abilities = abilities
      @breed_groupes = breed_groupes
      @breed_moves = breed_moves
      @master_moves = master_moves
      @hatch_step = hatch_step
      @items = items
      @baby = baby
    end
    # Safely return the height of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Numeric]
    def self.height(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).height
      end
      return $game_data_pokemon[0][0].height
    end
    # Safely return the weight of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Numeric]
    def self.weight(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).weight
      end
      return $game_data_pokemon[0][0].weight
    end
    # Safely return the id_bis of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.id_bis(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).id_bis
      end
      return $game_data_pokemon[0][0].id_bis
    end
    # Safely return the firs type of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.type1(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).type1
      end
      return $game_data_pokemon[0][0].type1
    end
    # Safely return the second type of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.type2(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).type2
      end
      return $game_data_pokemon[0][0].type2
    end
    # Safely return the base hp of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_hp(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_hp
      end
      return $game_data_pokemon[0][0].base_hp
    end
    # Safely return the base atk of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_atk(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_atk
      end
      return $game_data_pokemon[0][0].base_atk
    end
    # Safely return the base dfe of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_dfe(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_dfe
      end
      return $game_data_pokemon[0][0].base_dfe
    end
    # Safely return the base spd of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_spd(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_spd
      end
      return $game_data_pokemon[0][0].base_spd
    end
    # Safely return the base ats of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_ats(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_ats
      end
      return $game_data_pokemon[0][0].base_ats
    end
    # Safely return the base dfs of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_dfs(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_dfs
      end
      return $game_data_pokemon[0][0].base_dfs
    end
    # Safely return the hp ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_hp(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_hp
      end
      return $game_data_pokemon[0][0].ev_hp
    end
    # Safely return the atk ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_atk(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_atk
      end
      return $game_data_pokemon[0][0].ev_atk
    end
    # Safely return the dfe ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_dfe(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_dfe
      end
      return $game_data_pokemon[0][0].ev_dfe
    end
    # Safely return the spd ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_spd(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_spd
      end
      return $game_data_pokemon[0][0].ev_spd
    end
    # Safely return the ats ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_ats(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_ats
      end
      return $game_data_pokemon[0][0].ev_ats
    end
    # Safely return the dfs ev given by the Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.ev_dfs(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).ev_dfs
      end
      return $game_data_pokemon[0][0].ev_dfs
    end
    # Safely return the move set of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array<Integer, Integer>]
    def self.move_set(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).move_set
      end
      return $game_data_pokemon[0][0].move_set
    end
    # Safely return the tech set of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array<Integer>]
    def self.tech_set(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).tech_set
      end
      return $game_data_pokemon[0][0].tech_set
    end
    # Safely return the evolution level of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.evolution_level(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).evolution_level
      end
      return $game_data_pokemon[0][0].evolution_level
    end
    # Safely return the evolution id of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.evolution_id(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).evolution_id
      end
      return $game_data_pokemon[0][0].evolution_id
    end
    # Safely return the special evolution information of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Hash, nil]
    def self.special_evolution(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).special_evolution
      end
      return $game_data_pokemon[0][0].special_evolution
    end
    # Safely return the exp type of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.exp_type(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).exp_type
      end
      return $game_data_pokemon[0][0].exp_type
    end
    # Safely return the base exp of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_exp(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_exp
      end
      return $game_data_pokemon[0][0].base_exp
    end
    # Safely return the base loyalty of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.base_loyalty(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).base_loyalty
      end
      return $game_data_pokemon[0][0].base_loyalty
    end
    # Safely return the rareness of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.rareness(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).rareness
      end
      return $game_data_pokemon[0][0].rareness
    end
    # Safely return the female rate of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.female_rate(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).female_rate
      end
      return $game_data_pokemon[0][0].female_rate
    end
    # Safely return the abilities of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array(Integer, Integer, Integer)]
    def self.abilities(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).abilities
      end
      return $game_data_pokemon[0][0].abilities
    end
    # Safely return the breed groupes of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array(Integer, Integer)]
    def self.breed_groupes(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).breed_groupes
      end
      return $game_data_pokemon[0][0].breed_groupes
    end
    # Safely return the breed moves of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array<Integer>]
    def self.breed_moves(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).breed_moves
      end
      return $game_data_pokemon[0][0].breed_moves
    end
    # Safely return the master moves of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array<Integer>]
    def self.master_moves(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).master_moves
      end
      return $game_data_pokemon[0][0].master_moves
    end
    # Safely return the hatch step of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.hatch_step(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).hatch_step
      end
      return $game_data_pokemon[0][0].hatch_step
    end
    # Safely return the items held by a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Array<Integer, Integer>]
    def self.items(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).items
      end
      return $game_data_pokemon[0][0].items
    end
    # Safely return the baby of a Pokemon
    # @param id [Integer] id of the Pokemon in the database
    # @param form [Integer] form of the Pokemon
    # @return [Integer]
    def self.baby(id, form = 0)
      if id.between?(1, LastID)
        data = $game_data_pokemon[id]
        return(data[form] ? data[form] : data[0]).baby
      end
      return $game_data_pokemon[0][0].baby
    end
    # Safely return the db_symbol of an item
    # @param id [Integer] id of the item in the database
    # @return [Symbol]
    def self.db_symbol(id)
      if(id.between?(1, LastID))
        return ($game_data_pokemon[id][0].db_symbol || :__undef__)
      end
      return :__undef__
    end
    # Find a Pokemon using symbol
    # @note Returns first form if the form doesn't exists
    # @param symbol [Symbol]
    # @param form [Integer] requested form
    # @return [GameData::Pokemon]
    def self.find_using_symbol(symbol, form = 0)
      pokemon = $game_data_pokemon.find { |data| data[0].db_symbol == symbol }
      return $game_data_pokemon[0][0] unless pokemon
      pokemon.fetch(form) { pokemon.first }
    end
    # Get id using symbol
    # @param symbol [Symbol]
    # @return [Integer]
    def self.get_id(symbol)
      pokemon = $game_data_pokemon.index { |data| data[0].db_symbol == symbol }
      pokemon.to_i
    end
    # Convert a collection to symbolized collection
    # @param collection [Enumerable]
    # @param keys [Boolean] if hash keys are converted
    # @param values [Boolean] if hash values are converted
    # @return [Enumerable] the collection
    def self.convert_to_symbols(collection, keys: false, values: false)
      if collection.is_a?(Hash)
        new_collection = {}
        collection.each do |key, value|
          key = self.db_symbol(key) if keys and key.is_a?(Integer)
          if value.is_a?(Enumerable)
            value = self.convert_to_symbols(value, keys: keys, values: values)
          else
            value = self.db_symbol(value) if values and value.is_a?(Integer)
          end
          new_collection[key] = value
        end
        collection = new_collection
      else
        collection.each_with_index do |value, index|
          if value.is_a?(Enumerable)
            collection[index] = self.convert_to_symbols(value, keys: keys, values: values)
          else
            collection[index] = self.db_symbol(value) if value.is_a?(Integer)
          end
        end
      end
      collection
    end
  end
end
