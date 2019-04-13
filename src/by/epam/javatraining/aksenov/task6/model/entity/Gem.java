package by.epam.javatraining.aksenov.task6.model.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gem", propOrder = {
        "preciousness",
        "origin",
        "visual",
        "value",
        "name",
        "id"
})


public class Gem {
    @XmlElement(required = true)
    private boolean preciousness;

    @XmlElement(required = true)
    private String origin;

    @XmlElement(required = true)
    private Visual visual = new Visual();

    @XmlElement(required = true)
    private double value;

    @XmlAttribute(required = true)
    private String name;

    @XmlID
    @XmlAttribute
    private String id;

    public Gem() {
    }

    public Gem(boolean preciousness, String origin, Visual visual, double value, String name, String id) {
        this.preciousness = preciousness;
        this.origin = origin;
        this.visual = visual;
        this.value = value;
        this.name = name;
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isPreciousness() {
        return preciousness;
    }

    public void setPreciousness(boolean preciousness) {
        this.preciousness = preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", preciousness=" + preciousness +
                ", origin='" + origin + '\'' +
                ", visual=" + visual +
                ", value=" + value +
                "}\n";
    }

    @XmlRootElement
    @XmlType(name = "visual", propOrder = {
            "color",
            "transparency",
            "faceting"
    })
    public static class Visual {
        public enum Color {
            RED, GREEN, YELLOW, BLUE
        }

        private Color color;
        private double transparency;
        private int faceting;

        public Visual() {
        }

        public Visual(Color color, double transparency, int faceting) {
            this.color = color;
            this.transparency = transparency;
            this.faceting = faceting;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getTransparency() {
            return transparency;
        }

        public void setTransparency(double transparency) {
            this.transparency = transparency;
        }

        public int getFaceting() {
            return faceting;
        }

        public void setFaceting(int faceting) {
            this.faceting = faceting;
        }

        @Override
        public String toString() {
            return "Visual{" +
                    "color=" + color +
                    ", transparency=" + transparency +
                    ", faceting=" + faceting +
                    "}";
        }
    }
}
