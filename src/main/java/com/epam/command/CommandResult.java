package com.epam.command;

public class CommandResult {

    private final String page;
    private final boolean isRedirect;

    private CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public static CommandResult forward(String page){
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page){
        return new CommandResult(page, true);
    }
}
