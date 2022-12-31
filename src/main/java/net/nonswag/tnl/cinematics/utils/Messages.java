package net.nonswag.tnl.cinematics.utils;

import net.nonswag.core.api.annotation.FieldsAreNonnullByDefault;
import net.nonswag.core.api.file.formats.MessageFile;
import net.nonswag.core.api.language.Language;
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
        MessageFile english = MessageFile.getOrCreate(Language.AMERICAN_ENGLISH);
        english.setMessage(RECORD_NOT_FOUND, "%prefix% §cA record with this name does not exist");
        english.setMessage(RECORDING_ERROR, "%prefix% §cAn error has occurred while recording");
        english.setMessage(RECORDING_SAVED, "%prefix% §aSaved recording §6%recording%");
        english.setMessage(RECORDING_STOPPED, "%prefix% §aStopped the recording");
        english.save();
    }

    private static void initGerman() {
        MessageFile german = MessageFile.getOrCreate(Language.GERMAN);
        german.setMessage(RECORD_NOT_FOUND, "%prefix% §cEs existiert keine Aufnahme mit diesem Namen");
        german.setMessage(RECORDING_ERROR, "%prefix% §cWährend der Aufnahme ist ein Fehler aufgetreten");
        german.setMessage(RECORDING_SAVED, "%prefix% §aDie Aufnahme §6%recording%§a wurde gespeichert");
        german.setMessage(RECORDING_STOPPED, "%prefix% §aDie Aufnahme wurde gestoppt");
        german.save();
    }
}
