#encoding: utf-8

#> Prevent the game from launching
$GAME_LOOP = proc {}

#> Prevent GameData::Text from loading user language
$pokemon_party = nil

#> Change the default lang
module GameData
  module Text
    module_function
    def get_default_lang
      "en"
    end
    load
  end
end

NewText = "NewText"
Undef = "???"

UndefTexts = [NewText, Undef]

def make_symbol(text)
  if UndefTexts.include?(text)
    return :__undef__
  end
  text.downcase.gsub(' ', '_').to_sym
end

#> Load all abilities symbol
abilities_symbol = Array.new($game_data_abilities.size) do |id|
  text = GameData::Abilities.name(id)
  make_symbol(text)
end
save_data(abilities_symbol, 'Data/PSDK/Abilities_Symbols.rxdata')

#> Load all the item symbols
$game_data_item.each_with_index do |item, index|
  text = GameData::Item.name(index)
  item.db_symbol = make_symbol(text)
end
save_data($game_data_item, "Data/PSDK/ItemData.rxdata")

#> Load all the pokemon symbols
$game_data_pokemon.each_with_index do |pokemon, index|
  text = GameData::Pokemon.name(index)
  pokemon[0].db_symbol = make_symbol(text)
end
save_data($game_data_pokemon, "Data/PSDK/PokemonData.rxdata")

#> Load all the skill symbols
$game_data_skill.each_with_index do |skill, index|
  text = GameData::Skill.name(index)
  skill.db_symbol = make_symbol(text)
end
save_data($game_data_skill, "Data/PSDK/SkillData.rxdata")