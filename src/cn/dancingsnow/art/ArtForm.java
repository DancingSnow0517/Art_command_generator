package cn.dancingsnow.art;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ArtForm extends JFrame{
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel artLabel;
    private JLabel mainStatsLabel;
    private JTextField artField;
    private JComboBox mainStatsBox;
    private JComboBox stats2;
    private JComboBox stats1;
    private JComboBox stats3;
    private JComboBox stats4;
    private JLabel subStatsLabel;
    private JLabel subStats1;
    private JLabel subStats2;
    private JLabel subStats3;
    private JLabel subStats4;
    private JSpinner level1;
    private JSpinner level2;
    private JSpinner level3;
    private JSpinner level4;
    private JLabel artLevel;
    private JPanel subPanel;
    private JTextField levelField;
    private JTextField outputField;
    private JButton confirm;
    private JLabel outputLabel;
    private JLabel uidLabel;
    private JTextField uidField;
    private JButton copy;

    public ArtForm(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.pack();
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder builder = new StringBuilder("/giveart ");
                if (Objects.equals(uidField.getText(), "")) {
                    showMessage("请填写UID");
                    return;
                }
                builder.append(uidField.getText()).append(" ");

                if (Objects.equals(artField.getText(), "")) {
                    showMessage("请填写圣遗物ID");
                    return;
                }
                builder.append(artField.getText()).append(" ");

                String mainStatsID = mainStatsToId((String) mainStatsBox.getSelectedItem());
                if (Objects.equals(mainStatsID, "0")) return;
                builder.append(mainStatsID).append(" ");

                String subStatsID;

                subStatsID = subStatsToId((String) stats1.getSelectedItem());
                if (Objects.equals(subStatsID, "0")) return;
                builder.append(subStatsID).append(",").append(level1.getValue()).append(" ");

                subStatsID = subStatsToId((String) stats2.getSelectedItem());
                if (Objects.equals(subStatsID, "0")) return;
                builder.append(subStatsID).append(",").append(level2.getValue()).append(" ");

                subStatsID = subStatsToId((String) stats3.getSelectedItem());
                if (Objects.equals(subStatsID, "0")) return;
                builder.append(subStatsID).append(",").append(level3.getValue()).append(" ");

                subStatsID = subStatsToId((String) stats4.getSelectedItem());
                if (Objects.equals(subStatsID, "0")) return;
                builder.append(subStatsID).append(",").append(level4.getValue()).append(" ");

                int level = parseArtLevel(levelField.getText());
                if (level == -1) return;
                builder.append(level);

                outputField.setText(builder.toString());
            }
        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(outputField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                showMessage("复制成功");
            }
        });
    }

    public static String mainStatsToId(String stats) {
        try {
            MainStats mainStats = MainStats.valueOf(stats);
            return mainStats.id;
        }
        catch (Exception e){
            showMessage("无效的主词条");
            return "0";
        }
    }
    public static String subStatsToId(String stats) {
        try {
            SubStats subStats = SubStats.valueOf(stats);
            return subStats.id;
        }
        catch (Exception e){
            showMessage("无效的副词条");
            return "0";
        }
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static int parseArtLevel(String level) {
        try {
            int rt = Integer.parseInt(level);
            if (rt <=0 || rt > 21) {
                showMessage("圣遗物等级要在 1-21 之间");
                return -1;
            }
            return rt;
        }
        catch (Exception e) {
            showMessage("不正确的圣遗物等级");
            return -1;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new ArtForm("Art Command");
        frame.setVisible(true);
    }
}
