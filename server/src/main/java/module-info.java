module server {
    requires com.google.gson;
    exports org.example;
    opens org.example to com.google.gson;
    exports org.example.chat;
    opens org.example.chat to com.google.gson;
}