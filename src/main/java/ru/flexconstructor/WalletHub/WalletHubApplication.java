package ru.flexconstructor.WalletHub;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

/**
 * Access log parser test application.
 */
@SpringBootApplication
public class WalletHubApplication {

    /**
     * Starts up the application.
     *
     * @param args  CLI args.
     */
    public static void main(String[] args) {
        SpringApplication.run(WalletHubApplication.class, args);
    }

    /**
     * CLI promt provider.
     *
     * @return instance of {@link PromptProvider}.
     */
    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("parser:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
