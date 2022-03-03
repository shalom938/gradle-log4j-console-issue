module sample.utilities.test {
    requires sample.utilities;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    exports org.test.utilities.testing.it;
    opens org.test.utilities.testing.it;
}

