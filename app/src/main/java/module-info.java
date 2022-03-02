module sample.app {
    requires org.apache.commons.text;
    requires transitive org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.scripting; //required by log4j
    requires sample.util; //required by log4j
}

