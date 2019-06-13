# Module holding all the Game classes / modules
module Game
  # Module holding the Game interfaces
  module Interfaces
    # HUD shown during map processing
    class Map_HUD
      include ::Util::Item
      # Create a new Map HUD
      def initialize
        @viewport = Viewport.create(:main, 0)
        # Clone the party to detect the differences
        clone_party
        @party_stack = PartyStack_HUD.new(@viewport)
        @party_stack.data = @party
        # Shortcut disabled for now // focusing on party for now
        # clone_shortcut
        # @shortcut_stack = ShortcutStack_HUD.new(@viewport)
        # @shortcut_stack.data = @shortcuts
      end

      class PartyStack_HUD < UI::SpriteStack
        def initialize(viewport)
          super(viewport, viewport.rect.width - 64, 4)
          # Backdrop for pokemon hud // Also I want it to appear underneath Pokemon Sprites
          #   @Decor = Sprite::WithColor.new(@viewport)
          #.set_bitmap("sidebar", :interface)
          #.set_position(viewport.rect.width - 64, 0)
          6.times do |i|
            push(16, 30 * i, nil, type: Pokemon_HUD)
          end
        end

        # Set the party shown
        # @param party [Array<PFM::Pokemon>]
        def data=(party)
          6.times do |i|
            @stack[i].data = party[i]
          end
        end
      end
 
      class Pokemon_HUD < UI::SpriteStack
        def initialize(viewport)
          super(viewport, 0, 0, default_cache: :interface)
          @bg = push(0, 0, 'hud_pkm_background')
          @sprite_team = push(16, 14, nil, type: UI::PokemonIconSprite)
        end

        # Set the Pokmeon shown
        # @param pokemon [PFM::Pokemon]
        def data=(pokemon)
          return unless (self.visible = !pokemon.nil?)
          super
        end

        def set_origin(*)
          # Do nothing
        end
      end
  
      # Class that contain all the Shortcut buttons
      class ShortcutStack_HUD < UI::SpriteStack
        # Create a new ShortcutStack_HUD
        # @param viewport [Viewport]
        def initialize(viewport)
          super(viewport, viewport.rect.width - 38 * 4, viewport.rect.height - 38)
          4.times do |i|
            push(38 * i, 0, nil, type: ShortcutButton_HUD)
          end
        end

        # Update the button stack data
        # @param values [Array<Integer>]
        def data=(value)
          @stack.each_with_index do |sprite, index|
            sprite.data = value[index]
          end
        end
      end
       
      class ShortcutButton_HUD < UI::SpriteStack
        # Create a new ShortcutButton_HUD
        def initialize(viewport)
          super(viewport, 0, 0, default_cache: :interface)
          push(0, 0, 'hud_shortcut_background')
          @icon = push(4, 4, nil)
        end

        # Define the icon shown in the button
        # @param item_id [Integer] ID of the item in the database
        def data=(item_id)
          if (self.visible = item_id != 0)
            @icon.set_bitmap(GameData::Item.icon(item_id), :icon)
          end
        end

        def set_origin(*)
          # Do nothing
        end
      end

      # Dispos the Map HUD
      def dispose
        @viewport.dispose unless @viewport.disposed?
      end
  
      # Return if the HUD is disposed or not
      # @return [Boolean]
      def disposed?
        @viewport.disposed?
      end
  
      # Update the HUD
      def update
        return unless test_visibility
        # Test if the party was update
        if party_updated?
          clone_party
          @party_stack.data = @party
        end
        # Test if the shortcut were updated
        if shortcut_updated?
          clone_shortcut
          # @shortcut_stack.data = @shortcuts
        end
        return if update_input
        update_mouse
      end
        
      # Update the inputs (Input.trigger?())
      # @return [Boolean] if a key was pressed
      def update_input
        # Example :
        if Input.trigger?(:L3)
          # do something
          return true
        end
        # Ensure mouse_update can be called
        return false
      end
        
      # Update the mouse interactions
      def update_mouse
        return unless Mouse.trigger?(:left)
=begin
        # Detect the mouse in a shortcut
        @shortcut_stack.stack.each_with_index do |sprite, index|
          call_shortcut(index) if sprite.simple_mouse_in?
        end
        # Detect the mouse in a Pokemon
        @party_stack.stack.each_with_index do |sprite, index|
          call_pokemon(index) if sprite.simple_mouse_in?
        end
=end
      end
        
      # Call a shortcut
      # @param index [Integer] index of the shortcut in the shortcut stack
      def call_shortcut(index)
        item_id = @shortcuts[index]
        return if item_id == 0 or !$bag.has_item?(item_id)
        util_item_useitem(item_id)
      end
        
      # Call a Pokemon (interaction)
      # @param index [Integer] index of the pokemon in the party stack
      def call_pokemon(index)
        # TODO (if needed)
      end
        
      # Test the visibility of the HUD
      # @return [Boolean] if the HUD can update
      def test_visibility
        @viewport.visible = (!$game_temp.message_window_showing && $scene.is_a?(Scene_Map))
        return @viewport.visible
      end
        
      # Clone the party of the actor
      def clone_party
        @party = $actors.collect { |pokemon| pokemon.clone }
      end
        
      # Tell if the party has changed (different mon / different status)
      # @return [Boolean]
      def party_updated?
        return true if @party.size != $actors.size
        @party.each_with_index do |pokemon, index|
          original_pokemon = $actors[index]
          if original_pokemon.id != pokemon.id ||
             original_pokemon.form != pokemon.form ||
             original_pokemon.hp != pokemon.hp ||
             original_pokemon.status != pokemon.status
            return true
          end
        end
        return false
      end
        
      # Clone the shortcut
      def clone_shortcut
        @shortcuts = $bag.get_shortcuts
      end
        
      # Tell if the shorcut were updated
      # @return [Boolean]
      def shortcut_updated?
        @shortcuts != $bag.get_shortcuts
      end
        
      # Make sure the message works
      def display_message(*args)
        $scene.display_message(*args)
      end
  
      class << self
        # @return [Map_HUD, nil] current HUD
        attr_accessor :current
        # Instanciate the main HUD
        def instanciate_map_hud
          return if current && !current.disposed?
          @current = new
        end
  
        # Dispose the main HUD
        def dispose_map_hud
          return unless $scene.is_a?(Scene_Battle) || $scene.is_a?(Scene_NameInput)
          return if !current || current.disposed?
          current.dispose
        end
  
        # Update the main HUD
        def update_map_hud
          return if !current || current.disposed?
          current.update
        end
      end
    end
  end
end
  
# Add the HUD to the scheduler
Scheduler.add_message(:on_scene_switch, Scene_Title, 'Instanciate Map HUD', 1001,
                      Game::Interfaces::Map_HUD, :instanciate_map_hud)
Scheduler.add_message(:on_scene_switch, Scene_Battle, 'Instanciate Map HUD', 1001,
                      Game::Interfaces::Map_HUD, :instanciate_map_hud)
Scheduler.add_message(:on_scene_switch, Scene_Map, 'Dispose Map HUD', 1001,
                      Game::Interfaces::Map_HUD, :dispose_map_hud)
Scheduler.add_message(:on_update, Scene_Map, 'Update Map HUD', 1001,
                      Game::Interfaces::Map_HUD, :update_map_hud)
Scheduler.add_message(:on_update, GamePlay::Menu, 'Update Map HUD (from menu)', 1001,
                      Game::Interfaces::Map_HUD, :update_map_hud)