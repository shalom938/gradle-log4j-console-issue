module sample.utilities.test {
    requires sample.utilities;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    exports org.test.utilities.testing;
    opens org.test.utilities.testing;
}

