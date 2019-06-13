module GamePlay
  class Load
    remove_const :DEFAULT_GAME_LANGUAGE
    DEFAULT_GAME_LANGUAGE = 'en'
    LANGUAGE_CHOICE_LIST.clear
  end
end
