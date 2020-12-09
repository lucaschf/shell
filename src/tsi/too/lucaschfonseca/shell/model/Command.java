package tsi.too.lucaschfonseca.shell.model;

import tsi.too.lucaschfonseca.shell.api.ICommand;

import java.util.Arrays;;

public abstract class Command implements ICommand {
	private String name;
	private String[] args;

	public Command(String name, String... args) {
		super();
		this.name = name;
		this.args = args;
	}

	public Command(String name) {
		super();
		this.name = name;
		this.args = new String[] {};
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "Command [name=" + name + ", args=" + Arrays.toString(args) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != getClass())
			return false;
		
		return ((Command)obj).getName().equalsIgnoreCase(name);
	}
}
