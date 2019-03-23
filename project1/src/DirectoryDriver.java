/**
 * @author hongbo
 *
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author hongbo
 *
 */
public class DirectoryDriver {
    /**
     * Reference to the Trump button.
     */
    private JButton addButton;
    /**
     * Reference to the Trump button.
     */
    private JButton deleteButton;
    /**
     * Reference to the Trump button.
     */
    private JButton searchByAndrewIDButton;
    /**
     * Reference to the Trump button.
     */
    private JButton searchByFirstNameButton;
    /**
     * Reference to the Trump button.
     */
    private JButton searchByLastNameButton;
    /**
     * Reference to the text area.
     */
    private JTextField textAddFirstName;
    /**
     * Reference to the text area.
     */
    private JTextField textAddLastName;
    /**
     * Reference to the text area.
     */
    private JTextField textAddAndrewID;
    /**
     * Reference to the text area.
     */
    private JTextField textAddPhoneNumber;
    /**
     * Reference to the text area.
     */
    private JTextField textDeleteAndrewID;
    /**
     * Reference to the text area.
     */
    private JTextField textSearchKey;
    /**
     * Reference to the text area.
     */
    private JTextArea resultArea;
    /**
     * Reference to the text area.
     */
    private Directory directory = new Directory();
    /**
     * Constructor where JFrame and other components are instantiated.
     */
    public DirectoryDriver() {
        JFrame frame = new JFrame("Student Directory");
        frame.setSize(860, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                textSearchKey.requestFocus();
            }
        });
        JPanel pane = new JPanel();
        Dimension paneDimension = new Dimension(810, 70);
        int widthOfTextField = 9;
        JLabel label = new JLabel("Welcome to Student Diretory");
        Font font = new Font(Font.SERIF, Font.BOLD, 20);
        label.setFont(font);
        pane.add(label);
        JPanel paneAdd = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneAdd.setPreferredSize(paneDimension);
        TitledBorder titleAdd = BorderFactory.createTitledBorder("Add a new student");
        paneAdd.setBorder(titleAdd);
        paneAdd.add(new JLabel("First Name:"));
        textAddFirstName = new JTextField(widthOfTextField);
        paneAdd.add(textAddFirstName);
        paneAdd.add(new JLabel("Last Name:"));
        textAddLastName = new JTextField(widthOfTextField);
        paneAdd.add(textAddLastName);
        paneAdd.add(new JLabel("Andrew ID:"));
        textAddAndrewID = new JTextField(widthOfTextField);
        paneAdd.add(textAddAndrewID);
        paneAdd.add(new JLabel("Phone Number:"));
        textAddPhoneNumber = new JTextField(widthOfTextField);
        paneAdd.add(textAddPhoneNumber);
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textAddAndrewID.getText().equals("") || textAddFirstName.getText().equals("") || textAddLastName.getText().equals("")) {
                    resultArea.setText("Andrew ID, first name, and last name are required. Try again.");
                } else if (directory.getMapAndrewId().containsKey(textAddAndrewID.getText())) {
                    resultArea.setText("Andrew ID: " + textAddAndrewID.getText() + " already exists. Try again.");
                } else {
                    Student stu = new Student(textAddAndrewID.getText());
                    stu.setFirstName(textAddFirstName.getText());
                    stu.setLastName(textAddLastName.getText());
                    stu.setPhoneNumber(textAddPhoneNumber.getText());
                    directory.addStudent(new Student(stu));
                    resultArea.setText("A new entry was added.");
                    textAddAndrewID.setText("");
                    textAddFirstName.setText("");
                    textAddLastName.setText("");
                    textAddPhoneNumber.setText("");
                }
            }
        });
        paneAdd.add(addButton);
        JPanel paneDelete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneDelete.setPreferredSize(paneDimension);
        TitledBorder titleDelete = BorderFactory.createTitledBorder("Delete a student");
        paneDelete.setBorder(titleDelete);
        paneDelete.add(new JLabel("Andrew ID:"));
        textDeleteAndrewID = new JTextField(widthOfTextField);
        paneDelete.add(textDeleteAndrewID);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (directory.searchByAndrewId(textDeleteAndrewID.getText()) == null) {
                    resultArea.setText(textDeleteAndrewID.getText() + " No matches");
                } else {
                    Student stu = new Student(directory.searchByAndrewId(textDeleteAndrewID.getText()));
                    directory.deleteStudent(textDeleteAndrewID.getText());
                    resultArea.setText("");
                    resultArea.append("The entry was deleted\n");
                    resultArea.append(stu.toString());
                    textDeleteAndrewID.setText("");
                }
            }
        });
        paneDelete.add(deleteButton);
        JPanel paneSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneSearch.setPreferredSize(paneDimension);
        TitledBorder titleSearch = BorderFactory.createTitledBorder("Search student(s)");
        paneSearch.setBorder(titleSearch);
        paneSearch.add(new JLabel("Search Key:"));
        textSearchKey = new JTextField(widthOfTextField);
        textSearchKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (directory.searchByAndrewId(textSearchKey.getText()) == null) {
                    resultArea.setText(textSearchKey.getText() + " No matches");
                } else {
                    Student stu = new Student(directory.searchByAndrewId(textSearchKey.getText()));
                    resultArea.setText(stu.toString());
                    textSearchKey.setText("");
                }
            }
        });
        paneSearch.add(textSearchKey);
        searchByAndrewIDButton = new JButton("By Andrew ID");
        searchByAndrewIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (directory.searchByAndrewId(textSearchKey.getText()) == null) {
                    resultArea.setText(textSearchKey.getText() + " No matches");
                } else {
                    Student stu = new Student(directory.searchByAndrewId(textSearchKey.getText()));
                    resultArea.setText(stu.toString());
                    textSearchKey.setText("");
                }
            }
        });
        paneSearch.add(searchByAndrewIDButton);
        searchByFirstNameButton = new JButton("By First Name");
        searchByFirstNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (directory.searchByFirstName(textSearchKey.getText()).isEmpty()) {
                    resultArea.setText(textSearchKey.getText() + " No matches");
                } else {
                    List<Student> stuList = new ArrayList<Student>(directory.searchByFirstName(textSearchKey.getText()));
                    resultArea.setText("");
                    for (Student stu : stuList) {
                        resultArea.append(stu.toString() + "\n");
                    }
                    textSearchKey.setText("");
                }
            }
        });
        paneSearch.add(searchByFirstNameButton);
        searchByLastNameButton = new JButton("By Last Name");
        searchByLastNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (directory.searchByLastName(textSearchKey.getText()).isEmpty()) {
                    resultArea.setText(textSearchKey.getText() + " No matches");
                } else {
                    List<Student> stuList = new ArrayList<Student>(directory.searchByLastName(textSearchKey.getText()));
                    resultArea.setText("");
                    for (Student stu : stuList) {
                        resultArea.append(stu.toString() + "\n");
                    }
                    textSearchKey.setText("");
                }
            }
        });
        paneSearch.add(searchByLastNameButton);
        JPanel paneResults = new JPanel();
        paneResults.setPreferredSize(new Dimension(810, 230));
        TitledBorder titleResults = BorderFactory.createTitledBorder("Results");
        paneResults.setBorder(titleResults);
        resultArea = new JTextArea(10, 65);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(resultArea);
        paneResults.add(scroller);
        pane.add(paneAdd);
        pane.add(paneDelete);
        pane.add(paneSearch);
        pane.add(paneResults);
        frame.setContentPane(pane);
        frame.setVisible(true);
    }
    /**
     * Constructor where JFrame and other components are instantiated.
     * @param path value
     */
    public DirectoryDriver(String path) {
        this();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = null;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                for (int i = 0; i < attributes.length; i++) {
                    attributes[i] = attributes[i].substring(1, attributes[i].length() - 1);
                }
                Student stu = new Student(attributes[2]);
                stu.setFirstName(attributes[0]);
                stu.setLastName(attributes[1]);
                if (attributes.length == 4) {
                    stu.setPhoneNumber(attributes[3]);
                } else if (attributes.length == 3) {
                    stu.setPhoneNumber("");
                }
                directory.addStudent(new Student(stu));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args value
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new DirectoryDriver();
        } else if (args.length == 1) {
            new DirectoryDriver(args[0]);
        }
    }

}
