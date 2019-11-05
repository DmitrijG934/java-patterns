package nn.dgord.patterns.utils;

public interface Converter<S, D> {
    D convert(S src);
}
