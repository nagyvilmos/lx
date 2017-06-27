/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexa.core.controller;

/**
 *
 * @author william
 */
public abstract class CommandHandler
{
    protected final Environment environment = new Environment();


    public abstract void execute();
}
