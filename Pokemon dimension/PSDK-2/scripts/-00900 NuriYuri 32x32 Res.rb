# This script aim to set PSDK in a 32x32 resolution instead of the 16x16 one
# The screen will then be in 640x480 and the viewports will be in 640x480 (you can choose the resolution)

# Define the default width of the screen
screen_width = 640
# Define the default height of the screen
screen_height = 480
# Set the following variable to true if you want the ox_offset & oy_offset of particles to be doubled, set to false otherwise
double_particle_offset = true
# Force the rect: action of the particle to fix oy to the height
force_particle_rect_oy = true

#===
# Begining of the scrip
# Don't edit the lines after this comment!
#===
$global_binding = binding
# Config edit
module Config
  # Change the screen width
  remove_const :ScreenWidth
  ScreenWidth = $global_binding.local_variable_get(:screen_width)
  # Change the screen height
  remove_const :ScreenHeight
  ScreenHeight = $global_binding.local_variable_get(:screen_height)
  # Change the specific zoom
  remove_const :Specific_Zoom
  Specific_Zoom = 1
  # Change the default scale
  if ARGV.grep(/scale=/).empty?
    remove_const :ScreenScale
    const_set :ScreenScale, 1
  end
  # Change the main viewport properties
  module Viewport
  # Change the width
  remove_const :Width
  Width = $global_binding.local_variable_get(:screen_width)
  # Change the height
  remove_const :Height
  Height = $global_binding.local_variable_get(:screen_height)
  end
end

# Force the tilemap to be the RMXP one
class Spriteset_Map
  # Return the prefered tilemap class
  # @return [Class]
  def tilemap_class
    return Tilemap
  end
end

# Adjust the game player center X/Y position
class Game_Player
  remove_const :CENTER_X
  remove_const :CENTER_Y
  CENTER_X = ($global_binding.local_variable_get(:screen_width) / 2 - 16) * 4
  CENTER_Y = ($global_binding.local_variable_get(:screen_height) / 2 - 16) * 4
end

# Fix the shadow & the screen x/y
class Game_Character
  # Return the x position of the shadow of the character on the screen
  # @return [Integer]
  def shadow_screen_x
    return (@real_x - $game_map.display_x + 3) / 4 + 16 # +3 => +5
  end

  # Return the y position of the shadow of the character on the screen
  # @return [Integer]
  def shadow_screen_y
    return (@real_y - $game_map.display_y + 3) / 4 + 32 + (@offset_shadow_screen_y || 0) # +3 => +5
  end

  # Return the x position of the sprite on the screen
  # @return [Integer]
  def screen_x
    return (@real_x - $game_map.display_x + 3) / 4 + 16
  end

  # Return the y position of the sprite on the screen
  # @return [Integer]
  def screen_y
    y = (@real_y - $game_map.display_y + 3) / 4 + 32 # +3 => +5
    y += @offset_screen_y if @offset_screen_y
    if @jump_count >= @jump_peak
      n = @jump_count - @jump_peak
    else
      n = @jump_peak - @jump_count
    end
    return y - (@jump_peak * @jump_peak - n * n) / 2
  end
end

# Fix the tile zoom
class Sprite_Character
  remove_const :TILE_ZOOM
  TILE_ZOOM = 1
end

# Ajust the MapLinker offset
module Yuki
  module MapLinker
    remove_const :OffsetX
    remove_const :OffsetY
    OffsetX = $global_binding.local_variable_get(:screen_width) / 64
    OffsetY = $global_binding.local_variable_get(:screen_height) / 64
  end
end

# Ajust the tilemap script
class Tilemap
  remove_const :NX
  remove_const :NY
  NX = $global_binding.local_variable_get(:screen_width) / 32 + 2
  NY = $global_binding.local_variable_get(:screen_height) / 32 + 2
end

# Fix the particles
module Yuki
  class Particle_Object
    # Update the position of the particle sprite
    def update_sprite_position
      case @position_type
      when :center_pos, :grass_pos
        @sprite.x = ((@x * 128 - $game_map.display_x + 3) / 4 + 16)
        @sprite.y = ((@y * 128 - $game_map.display_y + 3) / 4 + 32)
        if @position_type == :center_pos || @sprite.y >= @character.screen_y
          @sprite.z = (screen_z + @add_z)
        else
          @sprite.z = (screen_z - 1)
        end
        @sprite.ox = @ox + @ox_off
        @sprite.oy = @oy + @oy_off
      when :character_pos
        @sprite.x = @character.screen_x / @zoom
        @sprite.y = @character.screen_y / @zoom
        @sprite.z = (@character.screen_z(0) + @add_z)
        @sprite.ox = @ox + @ox_off
        @sprite.oy = @oy + @oy_off
      end
    end

    if $global_binding.local_variable_get(:force_particle_rect_oy)
      add_handler(:rect) do |data|
        @sprite.src_rect.set(*data)
        @ox = data[2] / 2
        @oy = data[3]
      end
    end

    if $global_binding.local_variable_get(:double_particle_offset)
      add_handler(:oy_offset) { |data| @oy_off = data&.*(2) }
      add_handler(:ox_offset) { |data| @ox_off = data&.*(2) }
    end
  end
end
