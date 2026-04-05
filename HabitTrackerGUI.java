import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class HabitTrackerGUI {

    private final JFrame frame;
    private final JTextField habitField;
    private final DefaultListModel<String> listModel;
    private final JList<String> habitList;
    private ArrayList<Habit> habits;

    public HabitTrackerGUI() {
        habits = new ArrayList<>();

        frame = new JFrame("Habit Tracker");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // IMPORTANT: Set layout
        frame.setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        habitField = new JTextField(15);
        JButton addButton = new JButton("Add Habit");

        topPanel.add(habitField);
        topPanel.add(addButton);

        // Center List
        listModel = new DefaultListModel<>();
        habitList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(habitList);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        JButton completeButton = new JButton("Mark Complete");

        bottomPanel.add(completeButton);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add Habit
        addButton.addActionListener(e -> {
            String name = habitField.getText();
            if (!name.isEmpty()) {
                Habit habit = new DailyHabit(name);
                habits.add(habit);
                listModel.addElement(name + " | Streak: 0");
                habitField.setText("");
            }
        });

        // Mark Complete
        completeButton.addActionListener(e -> {
            int index = habitList.getSelectedIndex();
            if (index != -1) {
                Habit habit = habits.get(index);
                habit.markComplete();

                listModel.set(index,
                        habit.getName() + " | Streak: " + habit.getStreak());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HabitTrackerGUI());
    }
}