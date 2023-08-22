package eng.vitor.prcamel.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import eng.vitor.prcamel.model.entities.Segment;
import eng.vitor.prcamel.model.entities.Point;

import org.springframework.stereotype.Component;

@Component("spaceService")
public class SpaceService {

    private Set<Point> points = new HashSet<>();

    private Map<Segment, Set<Point>> segments = new HashMap<>();

    public void point(Point p) {
        if (!points.add(p) | points.size() == 1) {
            return;
        }
        List<Segment> segs = segments.keySet().stream().filter(seg -> seg.isOnSegment(p)).collect(Collectors.toList());
        segs.forEach(s -> segments.get(s).add(p));
        Set<Point> ps = segs.stream().flatMap(s -> segments.get(s).stream()).collect(Collectors.toSet());
        Set<Point> tmp = new HashSet<>(points);
        tmp.removeAll(ps);
        tmp.remove(p);
        tmp.forEach(p2 -> {
            segments.put(new Segment(p, p2), new HashSet<>(Arrays.asList(p, p2)));
        });
    }

    public List<Set<Point>> lines(int n) {
        return segments.values().stream().filter(ps -> ps.size() == n).collect(Collectors.toList());
    }

    public Set<Point> space() {
        return points;
    }

    public void delete() {
        points.clear();
        segments.clear();
    }

}
