package ru.flexconstructor.WalletHub;

import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.flexconstructor.WalletHub.shell.ParseCommands;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class WalletHubApplicationTests {

    @Autowired
    private Shell shell;


    @Test
    public void runShellTest() {
        Map<String, MethodTarget> commands = shell.listCommands();
        MethodTarget methodTarget = commands.get("parse");
        assertNotNull(methodTarget);
        assertTrue(methodTarget.getHelp().contains("Parse access log file"));
        assertTrue(methodTarget.getAvailability().isAvailable());
        assertEquals(ReflectionUtils.findMethod(
                ParseCommands.class, "parse", String.class, String.class, String.class, String.class).get(),
                methodTarget.getMethod());
    }

}
