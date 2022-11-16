package net.nonswag.tnl.cinematics.utils;

import net.nonswag.core.api.annotation.FieldsAreNonnullByDefault;
import net.nonswag.core.api.message.Message;
import net.nonswag.core.api.message.key.MessageKey;

@FieldsAreNonnullByDefault
public final class Messages {
    public static final MessageKey RECORD_NOT_FOUND = new MessageKey("record-not-found");
    public static final MessageKey RECORDING_ERROR = new MessageKey("recording-error");
    public static final MessageKey RECORDING_SAVED = new MessageKey("recording-saved");
    public static final MessageKey RECORDING_STOPPED = new MessageKey("recording-stopped");

    public static void init() {
        initEnglish();
        initGerman();
    }

    private static void initEnglish() {
        Message.getEnglish().setMessage(RECORD_NOT_FOUND, "%prefix% §cA record with this name does not exist");
        Message.getEnglish().setMessage(RECORDING_ERROR, "%prefix% §cAn error has occurred while recording");
        Message.getEnglish().setMessage(RECORDING_SAVED, "%prefix% §aSaved recording §6%recording%");
        Message.getEnglish().setMessage(RECORDING_STOPPED, "%prefix% §aStopped the recording");
        Message.getEnglish().save();
    }

    private static void initGerman() {
        Message.getGerman().setMessage(RECORD_NOT_FOUND, "%prefix% §cEs existiert keine Aufnahme mit diesem Namen");
        Message.getGerman().setMessage(RECORDING_ERROR, "%prefix% §cWährend der Aufnahme ist ein Fehler aufgetreten");
        Message.getGerman().setMessage(RECORDING_SAVED, "%prefix% §aDie Aufnahme §6%recording%§a wurde gespeichert");
        Message.getGerman().setMessage(RECORDING_STOPPED, "%prefix% §aDie Aufnahme wurde gestoppt");
        Message.getGerman().save();
    }
}
