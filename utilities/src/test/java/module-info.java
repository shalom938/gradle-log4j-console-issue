module sample.util.test {
    requires sample.util;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    exports org.test.utilities.testing;
    opens org.test.utilities.testing;
}

