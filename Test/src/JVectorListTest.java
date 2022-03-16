import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class JVectorListTest extends JFrame{

    private JList<String> mList;
    private JTextField mTvInput;
    private JButton mBtnAdd, mBtdDel;

    private Vector<String> mData = new Vector<String>();

    public JVectorListTest() {

        super("JList Test");

        buildGUI();
        registerListener();

        setBounds(100, 200, 400, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void buildGUI() {
        mData.add("apple");
        mData.add("orange");

        mList = new JList<String>(mData);
        mList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(createInputPanel(), BorderLayout.NORTH);
        add(new JScrollPane(mList), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

        mTvInput = new JTextField(20);
        p.add(mTvInput);

        mBtnAdd = new JButton("추가");
        p.add(mBtnAdd);

        mBtdDel = new JButton("삭제");
        p.add(mBtdDel);

        return p;
    }

    private void registerListener() {

        mBtnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = mTvInput.getText();

                mData.add(s);
                mList.updateUI(); //JList는 연결된 벡터를 자동을 갱신하지 않기 때문에 별로도 갱신해줘야한다

                mTvInput.setText("");

            }
        });


        mBtdDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int idx = mList.getSelectedIndex();

                mData.remove(idx);
                mList.updateUI(); //JList는 연결된 벡터를 자동을 갱신하지 않기 때문에 별로도 갱신해줘야한다

                mTvInput.setText("");

            }
        });


        mList.addListSelectionListener(new ListSelectionListener() { //JList 전용 리스너 (리스트에서 하나 클릭할떄?)

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(e.getValueIsAdjusting()) {
                    int idx = e.getFirstIndex();
                    System.out.println(idx);

                    mTvInput.setText(mList.getSelectedValue());
                }

            }
        });


    }


    public static void main(String[] args) {
        new JVectorListTest();
    }

}
