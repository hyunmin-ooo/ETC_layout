package kr.ac.kopo.etc_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GridDir05_5 extends AppCompatActivity {

    private EditText firstNumberEditText, secondNumberEditText;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    private TextView resultTextView;

    private EditText currentEditText; // 현재 선택된 EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_dir05_5); // XML 파일명은 grid_dir05_5

        // EditText 초기화
        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);

        // 숫자 버튼 초기화
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // 연산 버튼 초기화
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);

        // 결과 텍스트뷰 초기화
        resultTextView = findViewById(R.id.resultTextView);

        // 처음에는 첫 번째 EditText가 선택됨
        currentEditText = firstNumberEditText;
        firstNumberEditText.requestFocus(); // 앱 시작할 때 포커스 줌

        // EditText 클릭 리스너 설정
        firstNumberEditText.setOnClickListener(v -> currentEditText = firstNumberEditText);
        secondNumberEditText.setOnClickListener(v -> currentEditText = secondNumberEditText);

        // 숫자 버튼 클릭 리스너 설정
        View.OnClickListener numberClickListener = v -> {
            Button clickedButton = (Button) v;
            appendNumber(clickedButton.getText().toString());
        };

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);

        // 연산 버튼 클릭 리스너 설정
        buttonAdd.setOnClickListener(v -> calculate('+'));
        buttonSubtract.setOnClickListener(v -> calculate('-'));
        buttonMultiply.setOnClickListener(v -> calculate('*'));
        buttonDivide.setOnClickListener(v -> calculate('/'));
    }

    // 선택된 EditText에 숫자 추가
    private void appendNumber(String number) {
        if (currentEditText == null) {
            currentEditText = firstNumberEditText; // 안전장치
        }
        currentEditText.requestFocus();
        String currentText = currentEditText.getText().toString();
        currentEditText.setText(currentText + number);
    }

    // 계산 수행
    private void calculate(char operator) {
        String firstNumberStr = firstNumberEditText.getText().toString();
        String secondNumberStr = secondNumberEditText.getText().toString();

        if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
            Toast.makeText(this, "두 숫자를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int firstNumber = Integer.parseInt(firstNumberStr);
            int secondNumber = Integer.parseInt(secondNumberStr);
            int result = 0;

            switch (operator) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;
                case '-':
                    result = firstNumber - secondNumber;
                    break;
                case '*':
                    result = firstNumber * secondNumber;
                    break;
                case '/':
                    if (secondNumber == 0) {
                        Toast.makeText(this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            resultTextView.setText("계산 결과: " + result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "올바른 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
