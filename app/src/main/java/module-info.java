module sample.app {
    requires org.apache.commons.text;
    requires sample.utilities;
    exports org.test.app;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
//    requires org.apache.logging.log4j.slf4j;
    requires java.scripting; //required by log4j
}

