#encoding: utf-8

module PFM
  # The actor trainer data informations
  # 
  # Main object stored in $trainer and $pokemon_party.trainer
  # @author Nuri Yuri
  class Trainer
    # Name of the trainer as a boy (Default to Palbolsky)
    # @return [String]
    attr_accessor :name_boy
    # Name of the trainer as a girl (Default to Yuri)
    # @return [String]
    attr_accessor :name_girl
    # If the player is playing the girl trainer
    # @return [Boolean]
    attr_accessor :playing_girl
    # The internal ID of the trainer as a boy
    # @return [Integer]
    attr_accessor :id_boy
    # The internal ID of the trainer as a girl. It's equal to id_boy ^ 0x28F4AB4C
    # @return [Integer]
    attr_accessor :id_girl
    # The time in second when the Trainer object has been created (computer time)
    # @return [Integer]
    attr_accessor :start_time
    # The time the player has played as this Trainer object
    # @return [Integer]
    attr_accessor :play_time
    # The badges this trainer object has collected
    # @return [Array<Boolean>]
    attr_accessor :badges
    # The ID of the current region in which the trainer is
    # @return [Integer]
    attr_accessor :region
    # The game version in which this object has been saved or created
    # @return [Integer]
    attr_accessor :game_version
    # The current version de PSDK (update management). It's saved like game_version
    # @return [Integer]
    attr_accessor :current_version
    # Create a new Trainer
    def initialize
      @name_boy = _ext(9000, 2) #"Palbolsky"
      @name_girl = _ext(9000, 3) #"Yuri"
      $game_switches[Yuki::Sw::Gender] = @playing_girl = false
      $game_variables[Yuki::Var::Player_ID] = @id_boy = rand(0x3FFFFFFF)
      @id_girl = (@id_boy ^ 0x28F4AB4C)
      @start_time = Time.new.to_i
      @play_time = 0
      @badges = Array.new(6 * 8, false)
      @region = 0
      @game_version = Game_Version rescue 256
      @current_version = PSDK_Version rescue 0
      @time_counter = 0
      self.load_time
    end
    # Return the name of the trainer
    # @return [String]
    def name
      return @playing_girl ? @name_girl : @name_boy
    end
    # Change the name of the trainer
    # @param value [String] the new value of the trainer name
    def name=(value)
      if(@playing_girl)
        @name_girl=value
      else
        @name_boy=value
      end
      $game_actors[1].name = value
    end
    # Return the id of the trainer
    # @return [Integer]
    def id
      return @playing_girl ? @id_girl : @id_boy
    end
    # Redefine some variable RMXP uses with the right values
    def redefine_var
      $game_variables[Yuki::Var::Player_ID] = self.id
      $game_actors[1].name = self.name
      #redéfinir les badges
    end
    # Load the time counter with the current time
    def load_time
      @time_counter = Time.new.to_i
    end
    # Return the time counter (current time - time counter)
    # @return [Integer]
    def time_counter
      return Time.new.to_i-@time_counter
    end
    # Update the play time and reload the time counter
    # @return [Integer] the play time
    def update_play_time
      @play_time+=self.time_counter
      self.load_time
      return @play_time
    end
    # Return the number of badges the trainer got
    # @return [Integer]
    def badge_counter
      counter=0
      @badges.each do |i|
        counter+=1 if i
      end
      return counter
    end
    # Set the got state of a badge
    # @param badge_num [1, 2, 3, 4, 5, 6, 7, 8] the badge
    # @param region [Integer] the region id (starting by 1)
    # @param value [Boolean] the got state of the badge
    def set_badge(badge_num, region = 1, value = true)
      region -= 1
      badge_num -= 1
      if(region*8 >= @badges.size)
        print("Le jeu ne prévoit pas de badge pour cette région.",
        "PSDK_ERR n°000_006")
      else
        if(badge_num < 0 or badge_num > 7)
          print("Le numéro de badge indiqué est invalide, il doit être entre 1 et 8.",
          "PSDK_ERR n°000_007")
        else
          @badges[(region * 8) + badge_num] = value 
        end
      end
    end
    # Has the player got the badge ?
    # @param badge_num [1, 2, 3, 4, 5, 6, 7, 8] the badge
    # @param region [Integer] the region id (starting by 1)
    # @return [Boolean]
    def has_badge?(badge_num, region = 1)
      region -= 1
      badge_num -= 1
      if(region*8 >= @badges.size)
        print("Le jeu ne prévoit pas de badge pour cette région.",
        "PSDK_ERR n°000_006")
        return false
      else
        if(badge_num < 0 or badge_num > 7)
          print("Le numéro de badge indiqué est invalide, il doit être entre 1 et 8.",
          "PSDK_ERR n°000_007")
          return false
        else
          return @badges[(region * 8) + badge_num]
        end
      end
    end
    # Set the gender of the trainer
    # @param playing_girl [Boolean] if the trainer will be a girl
    def set_gender(playing_girl)
      @playing_girl = playing_girl
      $game_switches[Yuki::Sw::Gender] = playing_girl
      $game_variables[Yuki::Var::Player_ID] = self.id
      $game_actors[1].name = self.name
    end
  end
end
