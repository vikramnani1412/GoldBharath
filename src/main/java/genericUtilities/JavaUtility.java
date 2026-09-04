package genericUtilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;

import net.datafaker.Faker;

/**
 * Utility class providing Java-specific helper methods for generating
 * random data, formatted dates, time slots, and other common test data.
 *
 * <p>Methods are grouped into the following categories:</p>
 * <ul>
 *   <li>Random Number Generation</li>
 *   <li>Date and Time Utilities</li>
 *   <li>Time Slot Helpers (OPHC format)</li>
 *   <li>Fake Data Generators</li>
 * </ul>
 */
public class JavaUtility {

    // ─────────────────────────────────────────────
    // Random Number Generation
    // ─────────────────────────────────────────────

    /**
     * Generates a random integer in the range [0, 9,999,998].
     *
     * @return a random int strictly below 9,999,999
     */
    public int getRandomNum() {
        return new Random().nextInt(9999999);
    }

    /**
     * Generates a single random digit in the range [0, 9].
     *
     * @return a random int between 0 and 9 inclusive
     */
    public int getSingleRandomNumber() {
        return new Random().nextInt(10);
    }

    /**
     * Generates a large random long in the range [0, 999,999,999,999,999,999].
     * Safe on Java 8+ (uses {@link ThreadLocalRandom} instead of
     * {@code Random.nextLong(bound)}, which requires Java 17+).
     *
     * @return a random long value
     */
    public long getHighestRandomNumber() {
        long bound = 999_999_999_999_999_999L;
        return (long) (ThreadLocalRandom.current().nextDouble() * bound);
    }

    /**
     * Generates a random 10-digit Indian-style mobile number
     * starting with 9 (range: 9000000000–9999999999).
     *
     * @return mobile number as a {@link String}
     */
    public String getRandomMobileNum() {
        long mobileNumber = ThreadLocalRandom.current().nextLong(9_000_000_000L, 9_999_999_999L + 1);
        return String.valueOf(mobileNumber);
    }

    // ─────────────────────────────────────────────
    // Date and Time Utilities
    // ─────────────────────────────────────────────

    /**
     * Returns the current system date and time formatted as:
     * <pre>EEE MMM dd HH:mm:ss yyyy</pre>
     * Example: {@code Fri Jun 20 14:35:00 2025}
     *
     * @return current timestamp as a {@link String}
     */
    public String getSystemDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
    }

    /**
     * Returns the current date and time in a file-safe format:
     * <pre>dd-MMM-yyyy-HH-mm-ss</pre>
     * Example: {@code 20-Jun-2025-14-35-00}
     *
     * @return formatted date-time string suitable for filenames
     */
    public String getSystemDateInFormat() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy-HH-mm-ss"));
    }

    /**
     * Returns today's day of the month as an {@code int}.
     * Example: on June 20, returns {@code 20}.
     *
     * @return day of the month (1–31)
     */
    public int getTodaysDateinInt() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * Returns today's day of the month as a {@link String}.
     * Example: on June 20, returns {@code "20"}.
     *
     * @return day of the month as a String
     */
    public String getTodayExactDateInString() {
        return String.valueOf(LocalDate.now().getDayOfMonth());
    }

    /**
     * Returns today's day of the month as an {@code int}.
     * Alias for {@link #getTodaysDateinInt()}.
     *
     * @return day of the month (1–31)
     */
    public int getTodaysDayOfTheMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * Returns today's date as a {@link LocalDate}.
     *
     * @return current local date
     */
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }

    /**
     * Returns the current date as a {@link LocalDate}.
     * Alias for {@link #getTodayDate()}.
     *
     * @return current local date
     */
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Returns all remaining dates in the current month, starting from today
     * (inclusive).
     *
     * @return an ordered {@link List} of {@link LocalDate} objects
     */
    public List<LocalDate> getRemainingDatesOfMonth() {
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);

        List<LocalDate> dates = new ArrayList<>();
        for (int day = today.getDayOfMonth(); day <= currentMonth.lengthOfMonth(); day++) {
            dates.add(currentMonth.atDay(day));
        }
        return dates;
    }

    // ─────────────────────────────────────────────
    // Time Slot Helpers (OPHC Format)
    // ─────────────────────────────────────────────

    /**
     * Returns the current time formatted as {@code HH:mm} (24-hour).
     * Example: {@code "14:35"}
     *
     * @return current time string
     */
    public static String getCurrentTimeInOPHCformat() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Returns the upcoming 30-minute appointment slot from the current time,
     * formatted as a 12-hour range with AM/PM.
     *
     * <p>Rounding rules:</p>
     * <ul>
     *   <li>If current minute &lt; 30, next slot starts on the next :30 mark</li>
     *   <li>If current minute ≥ 30, next slot starts at the next :00 mark</li>
     * </ul>
     *
     * <p>Example: if now is 15:48, returns {@code "04:00 PM - 04:30 PM"}</p>
     *
     * @return time slot range as a {@link String}
     */
    public String getNextThirtyMinDateInFormatOPHC() {
        LocalTime now = LocalTime.now();
        int minute = now.getMinute() < 30 ? 0 : 30;
        LocalTime slotStart   = now.withMinute(minute).withSecond(0).withNano(0);
        LocalTime nextSlotStart = slotStart.plusMinutes(30);
        LocalTime nextSlotEnd   = nextSlotStart.plusMinutes(30);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return nextSlotStart.format(formatter) + " - " + nextSlotEnd.format(formatter);
    }

    /**
     * Returns a 30-minute appointment slot, one slot further ahead than
     * {@link #getNextThirtyMinDateInFormatOPHC()}.
     *
     * <p>Example: if now is 15:48, returns {@code "04:30 PM - 05:00 PM"}</p>
     *
     * @return time slot range as a {@link String}
     */
    public String increaseTimeByPlusThirtyMin() {
        LocalTime now = LocalTime.now();
        int minute = now.getMinute() < 30 ? 0 : 30;
        LocalTime slotStart   = now.withMinute(minute).withSecond(0).withNano(0);
        LocalTime nextSlotStart = slotStart.plusMinutes(60);
        LocalTime nextSlotEnd   = nextSlotStart.plusMinutes(30);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return nextSlotStart.format(formatter) + " - " + nextSlotEnd.format(formatter);
    }

    /**
     * Returns the next half-hour slot from the current time, formatted as
     * {@code HH:mm to HH:mm} (24-hour).
     *
     * <p>Rounding rules:</p>
     * <ul>
     *   <li>If current minute &lt; 30, next slot starts at :30 of the current hour</li>
     *   <li>If current minute ≥ 30, next slot starts at :00 of the next hour</li>
     * </ul>
     *
     * <p>Example: if now is 15:48, returns {@code "16:00 to 16:30"}</p>
     *
     * @return time slot string in 24-hour format
     */
    public static String getNextHalfHourSlotForOPHC() {
        LocalTime now = LocalTime.now();

        LocalTime startTime = (now.getMinute() < 30)
                ? now.withMinute(30).withSecond(0).withNano(0)
                : now.plusHours(1).withMinute(0).withSecond(0).withNano(0);

        LocalTime endTime = startTime.plusMinutes(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return startTime.format(formatter) + " to " + endTime.format(formatter);
    }

    /**
     * Returns all 30-minute appointment slots for the OPHC day schedule
     * (09:00 AM – 07:00 PM), formatted in uppercase 12-hour ranges.
     *
     * <p>Example entries:</p>
     * <ul>
     *   <li>{@code "09:00 AM - 09:30 AM"}</li>
     *   <li>{@code "09:30 AM - 10:00 AM"}</li>
     *   <li>…</li>
     *   <li>{@code "06:30 PM - 07:00 PM"}</li>
     * </ul>
     *
     * @return an ordered {@link List} of slot strings in uppercase
     */
    public static List<String> getAllTimeSlotsForOPHC() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end   = LocalTime.of(19, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        List<String> slots = new ArrayList<>();
        while (start.isBefore(end)) {
            LocalTime slotEnd = start.plusMinutes(30);
            slots.add((start.format(formatter) + " - " + slotEnd.format(formatter)).toUpperCase());
            start = slotEnd;
        }
        return slots;
    }

    // ─────────────────────────────────────────────
    // Fake Data Generators
    // ─────────────────────────────────────────────

    /**
     * Generates a random full name containing only letters and spaces
     * (all punctuation stripped via {@link Faker}).
     *
     * @return a random full name string
     */
    public String getRandomName() {
        return new Faker().name().fullName().replaceAll("[^a-zA-Z ]", "");
    }

    /**
     * Generates a random single first name in lowercase.
     * The name is guaranteed to be longer than 3 characters.
     *
     * @return a random first name string
     */
    public String getRandomSingleName() {
        Faker faker = new Faker();
        String firstName;
        do {
            firstName = faker.name()
                    .firstName()
                    .split("\\s+")[0]
                    .toLowerCase();
        } while (firstName.length() <= 3);
        return firstName;
    }
    /**
     * This method gives todays Date in dd-MM-yyyy format
     * @return
     */
    public String getTodaysDateIST() {
        return LocalDate.now(ZoneId.of("Asia/Kolkata"))
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    /**
     * This method gives todays Date in yyyy-MM-dd format
     * @return
     */
    public String getTodaysDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    @DataProvider(name = "mobileNumbers")
    public Object[][] mobileNumbers() throws Exception {

        ExcelFileUtility eUtil = new ExcelFileUtility();

        List<String> mobileNumbers = new ArrayList<>();

        // Read mobile numbers from row 27 to row 35, column 1
        for (int row = 27; row <= 35; row++) {

            String mobileNumber =
                    eUtil.readDataFromExcel("Doctor", row, 1);

            if (mobileNumber != null && !mobileNumber.trim().isEmpty()) {

                mobileNumbers.add(mobileNumber.trim());

                System.out.println(
                        "Mobile Number from Excel Row " + row +
                        " : " + mobileNumber
                );
            }
        }

        if (mobileNumbers.isEmpty()) {
            throw new Exception(
                    "No mobile numbers found in Excel rows 27 to 35."
            );
        }

        return new Object[][] {
            { mobileNumbers }
        };
    }
    
}
