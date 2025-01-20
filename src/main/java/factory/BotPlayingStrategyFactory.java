package factory;

import models.BotDifficultyLevel;
import strategies.botPlayingStrategy.BotPlayingStrategy;
import strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import strategies.botPlayingStrategy.HardBotPlayingStrategy;
import strategies.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }else{
            return new HardBotPlayingStrategy();
        }
    }
}
