# This script can be used with 00900 NuriYuri 32x32 Res.rb
# It makes your UI 2 times bigger in their viewport

# Define the original width of the screen (when you made your UI)
screen_width = 320
# Define the original height of the screen (when you made your UI)
screen_height = 240
# Set the following variable to true to also double the size of the Message Window
double_message_window = true

#===
# Begining of the scrip
# Don't edit the lines after this comment!
#===
$global_binding = binding
# Fix the viewport of the GamePlay:: Scene
module GamePlay
  class Base
    O_SCREEN_WIDTH = $global_binding.local_variable_get(:screen_width)
    O_SCREEN_HEIGHT = $global_binding.local_variable_get(:screen_height)
    # The main process at the begin of scene
    def main_begin
      if @viewport
        @viewport.ox = - O_SCREEN_WIDTH / 2
        @viewport.oy = - O_SCREEN_HEIGHT / 2
        @viewport.zoom = 2
      end
      Graphics.transition
    end
  end
end

if double_message_window
  module Yuki
    class Message
      O_SCREEN_WIDTH = $global_binding.local_variable_get(:screen_width)
      O_SCREEN_HEIGHT = $global_binding.local_variable_get(:screen_height)
      alias old_initialize initialize
      # Create a new Message handler
      # @param viewport [LiteRGSS::Viewport]
      # @param parent [GamePlay::Base, Scene_Map]
      def initialize(viewport, parent = $scene)
        old_initialize(viewport, parent)
        viewport.ox = - O_SCREEN_WIDTH / 2
        viewport.oy = - O_SCREEN_HEIGHT / 2
        viewport.zoom = 2
      end
      
      # Return the default window width
      # @return [Integer]
      def default_width
        viewport.rect.width / 2 - default_horizontal_margin * 2
      end

      # Calculate the current window position
      def calculate_position
        x = default_horizontal_margin
        case current_position
        when :top
          y = default_vertical_margin
        when :middle
          y = (viewport.rect.height / 2 - height) / 2
        when :bottom, :left
          y = viewport.rect.height / 2 - default_vertical_margin - height
        when :right
          y = viewport.rect.height / 2 - default_vertical_margin - height
          x = viewport.rect.height / 2 - x - width
        end
        set_position(x, y)
      end
    end
  end
end
