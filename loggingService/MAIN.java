

class LoggingService{

    public enum LogLevel{
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL;

        public boolean isAtLeast(LogLevel level){
            return this.ordinal() >= level.ordinal();
        }
    }
    public class Logger{
        private final List<Destination> destinations

        public Logger(List<Destination> destinations) {
            this.destinations = new ArrayList<>(destinations);
        }
        public void log(level, message){
            //if message is null we stil go ahead and log because fro a method 
            //thats going to be called thousands of times thats unnecessary noise.
            // 1.get the thread calling this 
            thread = currentThread().name;
            // 2.get the current time
            //capturing the timestamp at top because we want all write to have the same time.
            timestamp = current;
            // 3.create a new record with this and the level and message
            // 4.go through the destinations list and write the new record to each
            Record record = new Record(
                timestamp,
                thread,
                level = level,
                message = message,
            )

            for(Destination destination: destinations){
                //if the destination.write throws thats for destination to handle.
                destination.write(record);
            }
        }
    }

    public interface sink{
        public void write(String formatted) throws Exception;
    }

    class FileSink implements sink{
        String FilePath;

        public void write(String formatted){
            ...
        }
    }

    class ConsoleSink implements sink{

    }
    public interface Formatter {
        public String format(LogRecord record);
    }
    public class Destination{
        private final Formatter formatter;
        private final Level level;
        private final Sink sink;
        private final Lock lock;

        public Destination(Formatter formatter, LogLevel level, Sink sink){
            this.formatter = formatter;
            this.level = level;
            this.sink = sink;
            this.lock = new Lock();
        }

        public void write(Record record){
            if(!record.getLevel().isAtLeast(this.level))return;

            String formatted = formatter.format(record);
            lock.lock();
            try{
                sink.write(formatted);
            }catch(e){
                System.err.println("logger: sink write failed: " + e.getMessage());
            }finally{
                lock.unlock();
            }

        }

    }
}