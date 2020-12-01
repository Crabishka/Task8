public  class CmdParams {  // создаем класс, который хранит параметры

     String inputFile;
     String outputFile;
    // public boolean error; // ошибка выводится из функции parseArgs
     boolean help;
     boolean window;

    public static CmdParams parseArgs(String[] args) { // спорный момент про вывод ошибки
        CmdParams params = new CmdParams(); // создает объект, чтобы изменять параметры
        if (args.length == 0) {
            params.window = true;
            return params;
        }
        if (args[0].endsWith(".txt") && args[1].endsWith(".txt")){
            params.inputFile = args[0];
            params.outputFile = args[1];
            return params;
        }
        int ArgsLength = args.length; // запоминаем 1 раз, чтобы лишний раз не обращаться
        for (int i = 0; i < ArgsLength; i++) {   // тут много лишних проверок, на зато тут не важен порядок указания параметров
            if (args[i].equals("--window") || args[i].equals("-w")) {
                params.window = true;
                return params;
            }
            if (args[i].equals("-h") || args[i].equals("--help")) params.help = true;
            if (args[i].equals("-i") || args[i].equals("--input")) {
                if (i + 1 == ArgsLength || !args[i + 1].endsWith(".txt")) {
                    System.err.println("Missing Input file");
                    System.exit(-1);
                }
                params.inputFile = args[i + 1];
            }
            if (args[i].equals("-o") || args[i].equals("--output")) {
                if (i + 1 > ArgsLength || !args[i + 1].endsWith(".txt")) {
                    System.err.println("Missing Output file");
                    System.exit(-1);
                }
                params.outputFile = args[i + 1];
            }

        }
        if (params.inputFile == null || params.outputFile == null) {
            System.err.println("Missing Input or Output file");
            System.exit(-1);
        }
        /*
        // Начало спорного момента
        if (params.inputFile == null) { // если не нашли входной файл - находим первый txt файл
            for (String arg : args) {    // даже если у нас был только output, мы возьмем из output и перезапишем его
                if (arg.endsWith(".txt")) {
                    params.inputFile = arg;
                    break;
                }
            }
        }
        if (params.outputFile == null) { // если не нашли выходной файл - находим первый txt файл
            for (String arg : args) {    // даже если у нас был только input, мы возьмем из input и перезапишем его
                if (arg.endsWith(".txt")) {
                    params.outputFile = arg;
                    break;
                }
            }
        }
        // Конец спорного момента
        */

        return params; // возвращаем объект с параметрами
    }

}