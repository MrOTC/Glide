package net.hackedlecterns.glide.game;

import net.hackedlecterns.glide.model.CourseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameEventListener implements Listener {
    private final Game game;

    public GameEventListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent e) {
        if (e.getTo() == null) {
            return;
        }

//        if (!game.getPlayers().contains(e.getPlayer())) {
//            return;
//        }

        assert !game.getCourse().getCourseEvents().isEmpty();
        for (CourseEvent ce : game.getCourse().getCourseEvents()) {
//            System.out.printf("%s\n%s\n%s\n%s\n\n", ce.getRegion().getPos1(), ce.getRegion().getPos2(), e.getTo(), ce.getRegion().contains(e.getTo()));
            if (!ce.getRegion().contains(e.getFrom()) && ce.getRegion().contains(e.getTo())) {
                ce.onEnter(e.getPlayer(), game);
            }
            if (ce.getRegion().contains(e.getFrom()) && !ce.getRegion().contains(e.getTo())) {
                ce.onLeave(e.getPlayer(), game);
            }
        }
    }

}
