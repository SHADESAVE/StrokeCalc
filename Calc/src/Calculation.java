public class Calculation {
    public double calculate(String str) {
        return new Object() {
            int position = -1;
            int tokenMain;
            //Метод перехода на следущий символ
            void nextSymbol() {
                tokenMain = (++position < str.length()) ? str.charAt(position) : -1;
            }
            //Метод cравнения знаков операций ('+','-','*',... и так далее.)
            boolean trade(int tokenSymb) {
                if (tokenMain == tokenSymb) {
                    nextSymbol();
                    return true;
                }
                return false;
            }
            double start() {
                nextSymbol();
                double x = stepOne();
                if (position < str.length())
                    throw new RuntimeException("Выход за строку: " + (char) tokenMain);
                return x;
            }
            //Проверка на вторичные операции '+', '-'
            double stepOne() {
                double x = stepTwo();
                for (;;) {
                    if (trade('+'))
                        x += stepTwo();
                    else
                        if (trade('-'))
                            x -= stepTwo();
                        else
                            return x;
                }
            }
            //Проверка на самые приоритетные операции '*', '/'
            double stepTwo() {
                double x = stepThree();
                for (;;) {
                    if (trade('*'))
                        x *= stepThree();
                    else if (trade('/'))
                            x /= stepThree();
                          else return x;
                }
            }

            double stepThree() {
                //Унарные '+' и '-'
                if (trade('+'))
                    return stepThree();
                if (trade('-'))
                    return -stepThree();

                double x;
                int startPos = this.position;
                //Если скобки
                if (trade('(')) {
                    x = stepOne();
                    trade(')');
                } else if ((tokenMain >= '0' && tokenMain <= '9') || tokenMain == '.') {
                    while ((tokenMain >= '0' && tokenMain <= '9') || tokenMain == '.')
                        nextSymbol();
                    //Пробуем запарсить всё до следущего знака, кроме '.'
                    x = Double.parseDouble(str.substring(startPos, this.position));
                }  else {
                    throw new RuntimeException("Неизвестный символ: " + (char) tokenMain);
                }
                return x;
            }
        }.start();
    }
}
