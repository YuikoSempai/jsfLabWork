package model;

public class AreaCheck {
    static boolean check(Double x, Double y, Integer r) {
        boolean status = false;
        status = x >= 0 && y <= 0 && x * x + y * y <= r * r;
        if (x >= 0 && y >= 0) {
            double x1, y1, x2, y2, x3, y3;
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = r;
            x3 = r / 2.0;
            y3 = 0;
            double var1 = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
            double var2 = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
            double var3 = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);
            if ((var1 >= 0 && var2 >= 0 && var3 >= 0) || (var1 <= 0 && var2 <= 0 && var3 <= 0)) {
                status = true;
            }
        }
        if (x <= 0 && y >= 0 && x >= (-r) && y <= (r / 2.0)) {
            status = true;
        }
        return status;
    }
}
