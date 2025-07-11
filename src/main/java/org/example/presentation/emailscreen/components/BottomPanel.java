package org.example.presentation.emailscreen.components;

import org.example.model.Message;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.List;

public class BottomPanel extends JPanel {
    private final JSplitPane mainContainer;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final JList<Message> listOfSentEmails = new JList<>();
    private final JPanel recevierAndTopicPanel;
    private final JPanel topicContainer;
    private final JPanel receiverContainer;
    private JTextArea textArea;
    private JTextField receiverInputField;
    private JTextField topicInputField;

    public BottomPanel() {
        super(new BorderLayout());
        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());
        recevierAndTopicPanel = new JPanel(new GridBagLayout());
        topicContainer = new JPanel(new GridBagLayout());
        receiverContainer = new JPanel(new GridBagLayout());
        mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        setup();
    }

    private void setup() {
        mainContainer.setResizeWeight(0.2);
        mainContainer.setDividerLocation(0.2);
        mainContainer.setContinuousLayout(true);
        mainContainer.setEnabled(false);
        setLeftPanel();
        setRightPanel();
        this.add(mainContainer, BorderLayout.CENTER);
    }

    private void setLeftPanel() {
        sentEmailsLabel();
        listOfSentEmails();
    }

    private void setRightPanel() {
        receiverAndTopicContent();
        messageContent();
    }

    private void sentEmailsLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel textLabel = new JLabel("Sent emails: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        leftPanel.add(textLabel, constraints);
    }

    private void listOfSentEmails() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 10;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 10;
        constraints.weighty = 10;
        constraints.fill = GridBagConstraints.BOTH;
        leftPanel.add(listOfSentEmails, constraints);
    }

    private void receiverAndTopicContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.weighty = 2;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(recevierAndTopicPanel, constraints);
        createReceiverInputField();
        createTopicInputField();
    }

    private void messageContent() {
        GridBagConstraints constraints = new GridBagConstraints();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.weighty = 12;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(textArea, constraints);
    }

    public void bindOnClickSentMessage(Consumer<Message> listener) {
        listOfSentEmails.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Message selectedMessage = listOfSentEmails.getSelectedValue();
                if (selectedMessage != null) {
                    listener.accept(selectedMessage);
                }
            }
        });
    }

    public void setMessageContent(String content) {
        textArea.setText(content);
    }

    public void setReceiverInputFieldValue(String value) {
        receiverInputField.setText(value);
    }

    public void setTopicInputFieldValue(String value) {
        topicInputField.setText(value);
    }

    public void setListOfSentEmails(List<Message> messages) {
        DefaultListModel<Message> listModel = new DefaultListModel<>();
        listModel.addAll(messages);

        listOfSentEmails.setModel(listModel);
    }

    private void createReceiverInputField() {
        createReceiverLabel();
        createReceiverField();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 0.1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        recevierAndTopicPanel.add(receiverContainer, constraints);
    }

    private void createReceiverLabel() {
        JLabel label = new JLabel("TO:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        receiverContainer.add(label, constraints);
    }

    private void createReceiverField() {
        receiverInputField = new JTextField();
        receiverInputField.setEditable(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 9;
        constraints.weightx = 9.0;
        constraints.fill = GridBagConstraints.BOTH;
        receiverContainer.add(receiverInputField, constraints);
    }

    private void createTopicInputField() {
        createTopicLabel();
        createTopicField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 0.1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        recevierAndTopicPanel.add(topicContainer, constraints);
    }

    private void createTopicLabel() {
        JLabel label = new JLabel("TOPIC:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        topicContainer.add(label, constraints);
    }

    private void createTopicField() {
        topicInputField = new JTextField();
        topicInputField.setEditable(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 9;
        constraints.weightx = 9.0;
        constraints.fill = GridBagConstraints.BOTH;
        topicContainer.add(topicInputField, constraints);
    }
}
