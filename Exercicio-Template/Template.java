interface Template {
    abstract int compare(String o1, String o2);
}

class Sort_Por_letra implements Template {
    @Override
    public int compare(String o1, String o2) {
        char l1 = o1.charAt(o1.length() - 1);
        char l2 = o2.charAt(o2.length() - 1);
        return Character.compare(l1, l2);
    }

}

class Sort_Por_Tamanho implements Template {
    @Override
    public int compare(String o1, String o2) {
        int t1 = o1.length();
        int t2 = o2.length();
        return Integer.compare(t1, t2);
    }
}


