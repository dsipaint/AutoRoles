import java.io.IOException;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StopListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "disable") && args[1].equals("stacieroles"))
		{
			e.getChannel().sendMessage("*Disabling al's stacie roles code...*").complete();
			
			try
			{
				Data.writeData();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
			e.getJDA().shutdown();
			System.exit(0);
		}
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "shutdown"))
		{
			try
			{
				Data.writeData();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
			e.getJDA().shutdownNow();
			System.exit(0);
		}
	}
}
