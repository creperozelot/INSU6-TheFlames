package INSU.creperozelot.dc.bot.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class utils {
    public static boolean hasRole(Member member, String roleid) {

        List<String> ids = new ArrayList<>();

        for (Role role : member.getRoles()){

            ids.add(role.getId());

        }

        return ids.contains(roleid);
    }


}
