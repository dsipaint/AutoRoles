import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter
{
	public void onGuildMemberJoin(GuildMemberJoinEvent e)
	{
		if(!e.getGuild().getId().equals("768884364855083008"))
			return;
		
		for(String role : Data.roles)
			e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(role)).queue();
	}
}
