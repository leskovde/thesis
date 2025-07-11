package cz.cuni.mff.d3s.intellijplugin.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodUtilsTest {

    @Test
    public void givenBlankPrefix_whenCategorizingPrefix_thenReturnEmptyCategory() {
        String prefix = "";

        MethodUtils.NameCategory category = MethodUtils.categorizeMethodNamePrefix(prefix);

        assertEquals(MethodUtils.NameCategory.EMPTY, category);
    }

    @ParameterizedTest
    @ValueSource(strings = {"org", "org.", "org.example", "org.example."})
    public void givenPackagePrefix_whenCategorizingPrefix_thenReturnPackageCategory(String prefix) {
        MethodUtils.NameCategory category = MethodUtils.categorizeMethodNamePrefix(prefix);

        assertEquals(MethodUtils.NameCategory.PACKAGE, category);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "org.Test", "org.example.Test", "org.example.Test.", "org.example.Foo.Bar"})
    public void givenClassPrefix_whenCategorizingPrefix_thenReturnClassCategory(String prefix) {
        MethodUtils.NameCategory category = MethodUtils.categorizeMethodNamePrefix(prefix);

        assertEquals(MethodUtils.NameCategory.CLASS, category);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test.test", "org.Test.test", "org.example.Test.test", "org.example.Foo.Bar.test"})
    public void givenMethodPrefix_whenCategorizingPrefix_thenReturnMethodCategory(String prefix) {
        MethodUtils.NameCategory category = MethodUtils.categorizeMethodNamePrefix(prefix);

        assertEquals(MethodUtils.NameCategory.METHOD, category);
    }
}
