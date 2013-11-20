/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Attributes.AttributeGenerator;
import Attributes.AttributeInfo;
import Attributes.CodeAttribute;
import ConstantPoolTypes.ConstantGenerator;
import ConstantPoolTypes.ConstantPoolElem;
import Attributes.AttributeInfoUndefined;
import Infos.FieldInfo;
import Infos.MethodInfo;
import Instructions.InstructionElem;
import Instructions.InstructionGenerator;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.smartcardio.ATR;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class Parser {

    public static Parser instance;
    public Parser[] otherClasses;

    public static void Initialize(String path) throws FileNotFoundException, IOException {
        InputStream input = new BufferedInputStream(new FileInputStream(path));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int read = input.read(); read >= 0; read = input.read()) {
            output.write(read);
        }

        byte[] buffer = output.toByteArray();
        input.close();
        output.close();
        instance = new Parser(buffer);
    }
    private Queue<Byte> data;
    private int minorVersion;
    private int majorVersion;
    private int constantPoolCnt;
    private ConstantPoolElem[] constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfacesCnt;
    private int[] interfaces;
    private int fieldCnt;
    private FieldInfo[] fields;
    private int methodCnt;
    private MethodInfo[] methods;
    private int attributeCnt;
    private AttributeInfo[] attributes;
    private Stack<Stack<Object>> variableStack;

    public String GetClassName() {
        return constantPool[thisClass - 1].GetValue().toString();
    }

    public void ReadOtherClasses(String[] args) throws FileNotFoundException, IOException {
        otherClasses = new Parser[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            otherClasses[i - 1] = InitSubClass(args[i]);
            otherClasses[i - 1].Read();
        }
    }

    private Parser InitSubClass(String path) throws FileNotFoundException, IOException {
        InputStream input = new BufferedInputStream(new FileInputStream(path));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int read = input.read(); read >= 0; read = input.read()) {
            output.write(read);
        }

        byte[] buffer = output.toByteArray();
        input.close();
        output.close();
        return new Parser(buffer);
    }

    /*public ConstantPoolElem[] getConstantPool() {
     return constantPool;
     }*/
    public Stack<Stack<Object>> getVariableStack() {
        return variableStack;
    }

    public MethodInfo getMethodByName(String name, String className) {
        if (!constantPool[thisClass - 1].toString().equals(className)) {
            for (Parser sub : otherClasses) {
                if (sub.constantPool[sub.thisClass - 1].toString().equals(className)) {
                    for (MethodInfo i : sub.methods) {
                        if (name.equals(i.GetName(sub.constantPool))) {
                            return i;
                        }
                    }
                }
            }
            return null;
        }
        for (MethodInfo i : methods) {
            if (name.equals(i.GetName(constantPool))) {
                return i;
            }
        }
        return null;
    }

    private Parser(byte[] arr) {
        data = new LinkedList<>();
        for (byte b : arr) {
            data.add(b);
        }
    }

    public void Read() {
        if (!ReadMagicNumber()) {
            throw new RuntimeException("File type is not javaclass");
        }
        ReadVersions();
        ReadConstantPool();
        accessFlags = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        thisClass = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        superClass = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        ReadInterfaces();
        ReadFields();
        ReadMethods();
        ReadAttributes();
    }

    private boolean ReadMagicNumber() {
        byte a, b, c, d;
        a = data.poll();
        b = data.poll();
        c = data.poll();
        d = data.poll();
        return a == (byte) 0xCA && b == (byte) 0xFE && c == (byte) 0xBA && d == (byte) 0xBE;
    }

    private void ReadVersions() {
        minorVersion = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        majorVersion = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
    }

    private void ReadConstantPool() {
        constantPoolCnt = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        constantPool = new ConstantPoolElem[constantPoolCnt - 1];
        for (int i = 0; i < constantPoolCnt - 1; i++) {
            constantPool[i] = ConstantGenerator.createConstantPoolElem(data);
            if (constantPool[i] instanceof ConstantPoolTypes.CONSTANT_Double_info
                    || constantPool[i] instanceof ConstantPoolTypes.CONSTANT_Long_info) {
                constantPool[i + 1] = constantPool[i];
                i++;
            }
        }
        for (int i = 0; i < constantPoolCnt - 1; i++) {
            constantPool[i].AssignConstantPool(constantPool);
        }
    }

    private void ReadInterfaces() {
        interfacesCnt = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        interfaces = new int[interfacesCnt];
        for (int i = 0; i < interfacesCnt; i++) {
            interfaces[i] = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        }
    }

    private void ReadFields() {
        fieldCnt = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        fields = new FieldInfo[fieldCnt];
        for (int i = 0; i < fieldCnt; i++) {
            fields[i] = new FieldInfo(data);
        }
    }

    private void ReadMethods() {
        methodCnt = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        methods = new MethodInfo[methodCnt];
        for (int i = 0; i < methodCnt; i++) {
            methods[i] = new MethodInfo(data, constantPool);
        }
    }

    private void ReadAttributes() {
        attributeCnt = (StaticLibrary.ByteToInt(data.poll()) << 8) + StaticLibrary.ByteToInt(data.poll());
        attributes = new AttributeInfo[attributeCnt];
        for (int i = 0; i < attributeCnt; i++) {
            attributes[i] = AttributeGenerator.createAttributeElem(data, constantPool);
        }
    }

    public void Run() {
        variableStack = new Stack<>();
        variableStack.push(new Stack<>());
        // variableStack.push(new Stack<>());
        MethodInfo init = FindInit();
        MethodInfo main = FindMain();
        if (main == null || init == null) {
            throw new NoClassDefFoundError("No main function.");
        }
        /*   CodeAttribute iattrs = init.GetCodeAttribute(constantPool);

         iattrs.InitVariableStack(variableStack.pop());
         iattrs.ExecuteCode();*/
        System.out.println("--- CONSOLE OUTPUT ---");
        CodeAttribute mattrs = main.GetCodeAttribute(constantPool);

        mattrs.InitVariableStack(variableStack.pop());
        mattrs.ExecuteCode();
    }

    private MethodInfo FindMain() {
        for (MethodInfo i : methods) {
            if ("main".equals(i.GetName(constantPool))) {
                return i;
            }
        }
        return null;
    }

    private MethodInfo FindInit() {
        for (MethodInfo i : methods) {
            if ("<init>".equals(i.GetName(constantPool))) {
                return i;
            }
        }
        return null;
    }
}
