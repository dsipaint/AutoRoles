import java.io.IOException;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		if(!e.getMember().getPermissions().contains(Permission.ADMINISTRATOR))
			return;
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "addautorole"))
		{
			if(args.length == 1)
				return;
			
			String id = null;
			
			if(args[1].matches("\\d{18}"))
			{
				id = args[1];
				if(e.getGuild().getRoleById(id) == null)
				{
					e.getChannel().sendMessage("Role with id" + id + " could not be found").queue();
					return;
				}
			}
			else
			{
				String name = "";
				for(int i = 1; i < args.length; i++)
					name += args[1] + " ";
				
				name = name.substring(0, name.length() - 1);
				
				if(e.getGuild().getRolesByName(name, true).size() == 0)
				{
					e.getChannel().sendMessage("Role with name " + name + " could not be found").queue();
					return;
				}
				
				id = e.getGuild().getRolesByName(name, true).get(0).getId();
			}
			
			if(Data.roles.contains(id))
			{
				e.getChannel().sendMessage("Role " + e.getGuild().getRoleById(id).getName() + " is already an autorole!").queue();
				return;
			}
			
			Data.roles.add(id);
			
			try
			{
				Data.writeData();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
			e.getChannel().sendMessage("Added " + e.getGuild().getRoleById(id).getName() + " as an autorole").queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "delautorole"))
		{
			if(args.length == 1)
				return;
			
			String id = null;
			
			if(args[1].matches("\\d{18}"))
			{
				id = args[1];
				if(e.getGuild().getRoleById(id) == null)
				{
					e.getChannel().sendMessage("Role with id" + id + " could not be found").queue();
					return;
				}
			}
			else
			{
				String name = "";
				for(int i = 1; i < args.length; i++)
					name += args[1] + " ";
				
				name = name.substring(0, name.length() - 1);
				
				if(e.getGuild().getRolesByName(name, true).size() == 0)
				{
					e.getChannel().sendMessage("Role with name " + name + " could not be found").queue();
					return;
				}
				
				id = e.getGuild().getRolesByName(name, true).get(0).getId();
			}
			
			if(!Data.roles.contains(id))
			{
				e.getChannel().sendMessage("Role " + e.getGuild().getRoleById(id).getName() + " isn't an autorole!").queue();
				return;
			}
			
			Data.roles.remove(id);
			
			try
			{
				Data.writeData();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
			e.getChannel().sendMessage("Removed " + e.getGuild().getRoleById(id).getName() + " as an autorole").queue();
		}
	}
}
