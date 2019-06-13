#encoding: utf-8

unless PARGV[:worldmap] or PARGV[:"animation-editor"] or PARGV[:test] or PARGV[:tags]
  module Scheduler
    add_proc(:on_update, :any, "SoftReset", 10**99,
      proc {
        if Keyboard.press?(Keyboard::F12) and $scene.class != Yuki::SoftReset
          # Set the running to false if possible
          $scene.instance_variable_set(:@running, false) if $scene
          # Switching the scene to the soft reset
          $scene = Yuki::SoftReset.new
          # Telling soft reset is processing
          cc 0x03
          puts "Soft resetting..."
          cc 0x07
        end
      }
    )
  end

  module Yuki
    # Class that manage the soft reset
    class SoftReset
      # Main process of the scene
      def main
        # Disposing everything and freeing memory
        Audio.__reset__
        ObjectSpace.each_object(LiteRGSS::Viewport) { |v| v.dispose unless v.disposed? }
        GC.start
        ObjectSpace.each_object(LiteRGSS::Sprite) { |s| s.dispose unless s.disposed? }
        ObjectSpace.each_object(LiteRGSS::Text) { |t| t.dispose unless t.disposed? }
        ObjectSpace.each_object(LiteRGSS::Bitmap) { |b| b.dispose unless b.disposed? }
        GC.start
        # Reloading required ressources
        Graphics.init_sprite
        Graphics.transition(1)
        ts = 0.1
        sleep(ts) while Keyboard.press?(Keyboard::F12)
        Graphics.freeze
        $scene = Scheduler.get_boot_scene
      end

      def update
        return
      end
    end
  end
end
