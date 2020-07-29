package com.clarkbains.iims;

import java.io.InputStream;
import java.util.ArrayList;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
public class Server {
    ChatService cs;

    public Server() {
        System.out.println("Init Controller");
        cs = new ChatService();

    }

    @RequestMapping(value = "/chat")
    public String loginFormHandler(@NonNull @RequestParam("chat") String chat, @NonNull @RequestParam("name") String name) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("mainChat.html");
            String q = new String(in.readAllBytes());
            q = q.replace("{{chat}}", chat);
            q = q.replace("{{person}}", name);
            return q;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failure";

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("loginForm.html");
            String q = new String(in.readAllBytes());
            return q;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failure";
    }

    @GetMapping(path = "/msgs/{chat}")
    public String getMsgs(@NonNull @PathVariable String chat) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("msgIframe.html");
            String q = new String(in.readAllBytes());

            if (cs.getMessages(chat) == null) {
                cs.addMessage(chat, "System", "This is the start of the chat - " + chat);
            }

            String output = "";
            ArrayList<String> g = new ArrayList<>();
            for (int i = cs.getMessages(chat).msgs.size() - 1; i >= 0; i--) {
                g.add(cs.getMessages(chat).msgs.get(i));
            }
            for (String item : g)
                output += item + "<br>";
            return q.replace("{{msgs}}", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failure";

    }

    @GetMapping(path = "/msg")
    public void addMsg(@NonNull @RequestParam("chat") String chat, @NonNull @RequestParam("person") String person,
            @NonNull @RequestParam("msg") String msg) {
        cs.addMessage(chat, person, msg);
    }
}
