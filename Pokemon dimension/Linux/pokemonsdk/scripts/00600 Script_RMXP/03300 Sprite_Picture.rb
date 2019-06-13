#encoding: utf-8

# A sprite that show a Game_Picture on the screen
class Sprite_Picture < ShaderedSprite
  SPRITE_SHADER = <<-EOSHADER
  uniform vec4 tone;
  const vec3 lumaF = vec3(.299, .587, .114);
  uniform sampler2D texture;
  void main()
  {
    vec4 frag = texture2D(texture, gl_TexCoord[0].xy);
    float luma = dot(frag.rgb, lumaF);
    frag.rgb += tone.rgb;
    frag.rgb = mix(frag.rgb, vec3(luma), tone.w);
    frag.a *= gl_Color.a;
    // Result
    gl_FragColor = frag;
  }
  EOSHADER
  # Initialize a new Sprite_Picture
  # @param viewport [Viewport] the viewport where the sprite will be shown
  # @param picture [Game_Picture] the picture
  def initialize(viewport, picture)
    super(viewport)
    self.shader = Shader.new(SPRITE_SHADER)
    @picture = picture
    update
  end
  # Dispose the picture
  def dispose
    if self.bitmap != nil # /!\ Il va y avoir des pb de "Disposed Bitmap"
      self.bitmap.dispose unless self.bitmap.disposed?
    end
    super
  end
  # Update the picture sprite display with the information of the current Game_Picture
  def update
    super
    # ピクチャのファイル名が現在のものと異なる場合
    if @picture_name != @picture.name
      # ファイル名をインスタンス変数に記憶
      @picture_name = @picture.name
      # ファイル名が空でない場合
      #if @picture_name != ""
        # ピクチャグラフィックを取得
        self.bitmap = RPG::Cache.picture(@picture_name) unless @picture_name.empty?
      #end
    end
    # ファイル名が空の場合
    if @picture_name.empty?# == nil.to_s
      # スプライトを不可視に設定
      self.visible = false
      return
    end
    # スプライトを可視に設定
    self.visible = true
    # 転送元原点を設定
    if @picture.origin == 0
      self.ox = 0
      self.oy = 0
    else
      self.ox = self.bitmap.width / 2
      self.oy = self.bitmap.height / 2
    end
    # スプライトの座標を設定
    self.x = @picture.x
    self.y = @picture.y
    self.z = @picture.number
    # 拡大率、不透明度、ブレンド方法を設定
    self.zoom_x = @picture.zoom_x / 100.0
    self.zoom_y = @picture.zoom_y / 100.0
    self.opacity = @picture.opacity
    shader.blend_type = @picture.blend_type
    # 回転角度、色調を設定
    self.angle = @picture.angle
#    self.tone = @picture.tone
    tone = @picture.tone
    unless tone.eql?(@current_tone)
      shader.set_float_uniform('tone', tone)
      @current_tone = tone.clone
    end
  end
end
