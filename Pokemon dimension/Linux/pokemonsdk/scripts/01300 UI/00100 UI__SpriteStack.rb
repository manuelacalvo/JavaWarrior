#encoding: utf-8

# Module that holds every UI object
module UI
  # Class that helps to define a single object constitued of various sprites.
  # With this class you can move the sprites as a single sprite, change the data that generate the sprites and some other cool stuff
  class SpriteStack
    # X coordinate of the sprite stack
    # @return [Numeric]
    attr_reader :x
    # Y coordinate of the sprite stack
    # @return [Numeric]
    attr_reader :y
    # Data used by the sprites of the sprite stack to generate themself
    attr_reader :data
    # Get the stack
    attr_reader :stack

    # Create a new Sprite stack
    # @param viewport [LiteRGSS::Viewport] the viewport where the sprites will be shown
    # @param x [Numeric] the x position of the sprite stack
    # @param y [Numeric] the y position of the sprite stack
    # @param default_cache [Symbol] the RPG::Cache function to call when setting the bitmap
    def initialize(viewport, x = 0, y = 0, default_cache: :interface)
      @viewport = viewport
      @stack = []
      @x = x
      @y = y
      @default_cache = default_cache
    end
    # Push a sprite to the stack
    # @param x [Numeric] the relative x position of the sprite in the stack (sprite.x = stack.x + x)
    # @param y [Numeric] the relative y position of the sprite in the stack (sprite.y = stack.y + y)
    # @param args [Array] the arguments after the viewport argument of the sprite to create the sprite
    # @param rect [Array, nil] the src_rect.set arguments if required
    # @param type [Class] the class to use to generate the sprite
    # @param ox [Numeric] the ox of the sprite
    # @param oy [Numeric] the oy of the sprite
    # @return [type.new(@viewport, *args)] the pushed sprite
    def push(x, y, bmp, *args, rect: nil, type: LiteRGSS::Sprite, ox: 0, oy: 0)
      sprite = type.new(@viewport, *args)
      sprite.set_position(@x + x,@y + y).set_origin(ox, oy)
      sprite.set_bitmap(bmp, @default_cache) if bmp
      sprite.src_rect.set(*rect) if rect.is_a?(Array)
      sprite.src_rect = rect if rect.is_a?(LiteRGSS::Rect)
      return push_sprite(sprite)
    end
    # Push a sprite object to the stack
    # @param sprite [LiteRGSS::Sprite, LiteRGSS::Text]
    # @return [sprite]
    def push_sprite(sprite)
      @stack << sprite
      return sprite
    end
    # Add a text inside the stack, the offset x/y will be adjusted
    # @param x [Integer] the x coordinate of the text surface
    # @param y [Integer] the y coordinate of the text surface
    # @param width [Integer] the width of the text surface
    # @param height [Integer] the height of the text surface
    # @param str [String] the text shown by this object
    # @param align [0, 1, 2] the align of the text in its surface (best effort => no resize), 0 = left, 1 = center, 2 = right
    # @param outlinesize [Integer, nil] the size of the text outline
    # @param type [Class] the type of text
    # @param color [Integer] the id of the color
    # @return [LiteRGSS::Text] the text object
    def add_text(x, y, width, height, str, align = 0, outlinesize = Text::Util::DEFAULT_OUTLINE_SIZE, type: Text, color: 0)
      text = type.new(@font_id.to_i, @viewport, x + @x, y - Text::Util::FOY + @y, width, height, str, align, outlinesize)
      text.load_color(color) if color != 0
      text.draw_shadow = outlinesize.nil?
      @stack << text
      return text
    end
    # Change the x coordinate of the sprite stack
    # @param value [Numeric] the new value
    def x=(value)
      delta = value - @x
      @x = value
      @stack.each { |sprite| sprite.x += delta }
    end
    # Change the y coordinate of the sprite stack
    # @param value [Numeric] the new value
    def y=(value)
      delta = value - @y
      @y = value
      @stack.each { |sprite| sprite.y += delta }
    end
    # Change the x and y coordinate of the sprite stack
    # @param x [Numeric] the new x value
    # @param y [Numeric] the new y value
    # @return [self]
    def set_position(x, y)
      delta_x = x - @x
      delta_y = y - @y
      return move(delta_x, delta_y)
    end
    # Move the sprite stack
    # @param delta_x [Numeric] number of pixel the sprite stack should be moved in x
    # @param delta_y [Numeric] number of pixel the sprite stack should be moved in y
    # @return [self]
    def move(delta_x, delta_y)
      @x += delta_x
      @y += delta_y
      @stack.each { |sprite| sprite.set_position(sprite.x + delta_x, sprite.y + delta_y) }
      return self
    end
    # If the sprite stack is visible
    # @note Return the visible property of the first sprite
    # @return [Boolean]
    def visible
      return false if @stack.empty?
      return @stack.first.visible
    end
    # Change the visible property of each sprites
    # @param value [Boolean]
    def visible=(value)
      @stack.each { |sprite| sprite.visible = value }
    end
    # Detect if the mouse is in the first sprite of the stack
    # @param mx [Numeric] mouse x coordinate
    # @param my [Numeric] mouse y coordinate
    # @return [Boolean]
    def simple_mouse_in?(mx = LiteRGSS::Mouse.x, my = LiteRGSS::Mouse.y)
      return false if @stack.empty?
      return @stack.first.simple_mouse_in?(mx, my)
    end
    # Translate the mouse coordinate to mouse position inside the first sprite of the stack
    # @param mx [Numeric] mouse x coordinate
    # @param my [Numeric] mouse y coordinate
    # @return [Array(Numeric, Numeric)]
    def translate_mouse_coords(mx = LiteRGSS::Mouse.x, my = LiteRGSS::Mouse.y)
      return 0,0 if @stack.empty?
      return @stack.first.translate_mouse_coords(mx, my)
    end
    # Set the data source of the sprites
    # @param v [Object]
    def data=(v)
      @data = v
      @stack.each do |sprite|
        sprite.data = v if sprite.respond_to?(:data=)
      end
    end
    # yield a block on each sprite
    # @param block [Proc]
    def each(&block)
      @stack.each(&block)
    end
    # Dispose each sprite of the sprite stack and clear the stack
    def dispose
      @stack.each do |sprite|
        sprite.dispose
      end
      @stack.clear
    end
    #>>> Section from Yuki::Sprite <<<
    # If the sprite has a self animation
    # @return [Boolean]
    attr_accessor :animated
    # If the sprite is moving
    # @return [Boolean]
    attr_accessor :moving
    # Update sprite (+move & animation)
    def update
      update_animation(false) if @animated
      update_position if @moving
      @stack.each { |sprite| sprite.update if sprite.respond_to?(:update) }
    end
    # Move the sprite to a specific coordinate in a certain amount of frame
    # @param x [Integer] new x Coordinate
    # @param y [Integer] new y Coordinate
    # @param nb_frame [Integer] number of frame to go to the new coordinate
    def move_to(x, y, nb_frame)
      @moving = true
      @move_frame = nb_frame
      @move_total = nb_frame
      @new_x = x
      @new_y = y
      @del_x = self.x - x
      @del_y = self.y - y
    end
    # Update the movement
    def update_position
      @move_frame-=1
      @moving = false if @move_frame == 0
      set_position(
        @new_x + (@del_x * @move_frame) / @move_total,
        @new_y + (@del_y * @move_frame) / @move_total
      )
    end
    # Start an animation
    # @param arr [Array<Array(Symbol, *args)>] Array of message
    # @param delta [Integer] Number of frame to wait between each animation message
    def anime(arr,delta = 1)
      @animated = true
      @animation = arr
      @anime_pos = 0
      @anime_delta = delta
      @anime_count = 0
    end
    # Update the animation
    # @param no_delta [Boolean] if the number of frame to wait between each animation message is skiped
    def update_animation(no_delta)
      unless no_delta
        @anime_count += 1
        return if(@anime_delta > @anime_count)
        @anime_count = 0
      end
      anim = @animation[@anime_pos]
      self.send(*anim) if anim[0] != :send and anim[0].class == Symbol
      @anime_pos += 1
      @anime_pos = 0 if @anime_pos >= @animation.size
    end
    # Force the execution of the n next animation message
    # @note this method is used in animation message Array
    # @param n [Integer] Number of animation message to execute
    def execute_anime(n)
      @anime_pos += 1
      @anime_pos = 0 if @anime_pos >= @animation.size
      n.times do
        update_animation(true)
      end
      @anime_pos -= 1
    end
    # Stop the animation
    # @note this method is used in the animation message Array (because animation loops)
    def stop_animation
      @animated = false
    end
    # Change the time to wait between each animation message
    # @param v [Integer]
    def anime_delta_set(v)
      @anime_delta = v
    end
    # Gets the opacity of the SpriteStack
    # @return [Integer]
    def opacity
      return 0 unless sprite = @stack.first
      return sprite.opacity
    end
    # Sets the opacity of the SpriteStack
    # @param value [Integer] the new opacity value
    def opacity=(value)
      @stack.each { |sprite| sprite.opacity = value if sprite.respond_to?(:opacity=) }
    end
    # Gets the z of the SpriteStack
    # @return [Numeric]
    def z
      return 0 unless sprite = @stack.first
      return sprite.z
    end
    # Sets the z of the SpriteStack
    def z=(value)
      @stack.each { |sprite| sprite.z = value }
    end
  end
end
