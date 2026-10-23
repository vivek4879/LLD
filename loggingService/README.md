# Logging Service (LLD)

A small, thread-safe logging library design in Java. A `Logger` fans each log call out to one or more `Destination`s. Each destination has its own minimum level, format, and output target.

## Overview

```
Logger ──► Destination ──► Formatter  (LogRecord -> String)
                      └──► Sink       (String -> console / file / ...)
```

| Component | Responsibility |
|---|---|
| `LogLevel` | Ordered severity: `DEBUG < INFO < WARN < ERROR < FATAL`; `isAtLeast()` for threshold checks |
| `LogRecord` | Immutable record: timestamp, level, message, thread name |
| `Formatter` | Turns a record into a string (`PlainTextFormatter`, `JsonFormatter`) |
| `Sink` | Writes a formatted string somewhere (`ConsoleSink`, `FileSink`) |
| `Destination` | Combines formatter + min level + sink; filters, formats, and writes under a lock |
| `Logger` | Builds the record once and dispatches it to every destination |

## Key Design Decisions

- **Formatter and Sink are independent.** Any format can go to any target (e.g. JSON to a file, plain text to console), so adding one never touches the other.
- **Composition over inheritance for destinations.** A type-flagged class leaves most fields null, and an abstract base class makes wrappers (buffering, retry) hard to add. Behavior is composed from interfaces instead.
- **Immutable destination list.** The `Logger` copies the list at construction, so `log()` iterates it without locking.
- **Timestamp and thread name captured once** at the top of `log()`, so every destination sees the same values.
- **Per-destination lock.** Each `Destination` locks around `sink.write`, so a record's bytes never interleave with another's on the same destination, while different destinations don't block each other.
- **Failure isolation.** A sink that throws is caught in `Destination`, reported to `System.err`, and does not stop other destinations or the caller.

## Usage (intended API)

```java
Logger logger = new Logger(List.of(
    new Destination(new PlainTextFormatter(), LogLevel.INFO,  new ConsoleSink()),
    new Destination(new JsonFormatter(),      LogLevel.ERROR, new FileSink("app.log"))
));

logger.info("server started");   // console only
logger.error("db unreachable");  // console and app.log
```

## Status

Design and skeleton only. `MAIN.java` holds pseudocode sketches; formatters, sinks, and the `LogRecord` class are not yet implemented.

## Extending

- New output (network, rolling file): implement `Sink`.
- New format (XML, logfmt): implement `Formatter`.
- Buffering or retry: wrap a `Sink`.

---

# Design Notes

Requirements:
1. Five severity levels: DEBUG < INFO < WARN < ERROR < FATAL.
2. Each record carries timestamp, level, message, emitting thread name.
3. Logger writes each record to one or more destinations, set at startup.
4. Each destination has its own min-level threshold and its own format.
   Format and destination type vary independently.
5. Concurrent calls are safe. A record's bytes never interleave with
   another record's bytes on the same destination.


Out of scope:
- Hot-reloading config at runtime
- Async / buffered writes
- Remote / network destinations in v1 (design should accommodate)
- Hierarchical / named loggers (com.app.service inheriting from com.app)


enum LogLevel:
    DEBUG
    INFO
    WARN
    ERROR
    FATAL
    // ordered: DEBUG < INFO < WARN < ERROR < FATAL

class LogRecord:
    - timestamp: Instant
    - level: LogLevel
    - message: String
    - threadName: String

    + LogRecord(timestamp, level, message, threadName)
    + getters for all fields

interface Formatter:
    + format(record: LogRecord) -> String

class PlainTextFormatter implements Formatter
class JsonFormatter implements Formatter

interface Sink:
    + write(formatted: String)

class ConsoleSink implements Sink
class FileSink implements Sink:
    - filePath: String

class Destination:
    - formatter: Formatter
    - minLevel: LogLevel
    - sink: Sink

    + Destination(formatter, minLevel, sink)
    + write(record: LogRecord)

//Easiest way is to make a destination concrete class with a type field which will be either type file or type console to start with.
//but issue is every time we want to add another type we have to keep adding more if statements., new type will also ad  new fields that are relevant to only
//that one target.So we end up with a class where most fields are null for for any instance.

//So instead we do Destination abstract base class and different sinks will extend it. The problem wiht this is if you wanna add wrappers later,
eample if we have consoleDestination extending destination and fileDestination extending destination, if we later also wanna add buffered or retry feature, we  have to add it to all types. We cannot make that as anpother class as java does not allow inheriting from two classes becaquse of the diamond problem.
so better to make it an interface.




class Logger:
    - destinations: List<Destination> //immutable after construction

    //we have a list of destinations our logger orchestrator can log to. 
    //this list is immutable. If we keep it mutable we will have to add a lock on every
    //log call because it will go through this list and we cannot have it mutaing while its been read.
    + Logger(destinations: List<Destination>)
    //log will take the level, the message and log to every destination in the destinations list
    + log(level: LogLevel, message: String)

    //these are convenience methods so that the user does not have to type the level along with the log method
    //now the user can directly just call the level with the message.
    + debug(message: String)
    + info(message: String)
    + warn(message: String)
    + error(message: String)
    + fatal(message: String)