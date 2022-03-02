module sample.util {
    requires transitive sample.list;
    requires transitive org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.slf4j;
    requires java.scripting; //required by log4j
    exports org.test.utilities;
}

