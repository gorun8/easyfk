/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.base.util;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import cn.gorun8.easyfk.base.util.cache.UtilCache;

/**
 * Performs regular expression matching and compiled regular expression
 * pattern caching.
 */
public class CompilerMatcher {

    public static final String module = CompilerMatcher.class.getName();

    public static UtilCache<String, Pattern> compiledPatterns = UtilCache.createUtilCache("regularExpression.compiledPatterns", false);

    private Perl5Compiler compiler = new Perl5Compiler();
    private Perl5Matcher matcher = new Perl5Matcher();
    private Perl5Util perl5Util = new Perl5Util();

    /**
     * This class is *not* thread-safe so it must be
     * accessed from a java.lang.ThreadLocal instance for multi-thread usage.
     * ThreadLocal causes slightly extra memory usage, but allows for faster
     * thread-safe processing than synchronization would afford.
     *
     * @return returns the ThreadLocal
     */
    public static ThreadLocal<CompilerMatcher> getThreadLocal() {
        return new ThreadLocal<CompilerMatcher>() {

            protected CompilerMatcher initialValue() {
                return new CompilerMatcher();
            }
        };
    }

    /**
     * Returns true if the compiled version of the patternString regular
     * expression argument matches the aString argument.
     * Case sensitive
     *
     * @param aString a string
     * @param patternString a string pattern
     * @return returns true if the compiled version of the patternString regular expression argument matches the aString argument
     * @throws MalformedPatternException
     */
    public boolean matches(String aString, String patternString) throws MalformedPatternException {
        return this.matches(aString, patternString, true);
    }

    /**
     * Returns true if the compiled version of the patternString regular
     * expression argument matches the aString argument.
     * @param aString a string
     * @param patternString a string pattern
     * @param caseSensitive case sensitive true/false
     * @return returns true if the compiled version of the patternString regular expression argument matches the aString argument
     * @throws MalformedPatternException
     */
    public boolean matches(String aString, String patternString, boolean caseSensitive) throws MalformedPatternException {
        return this.matcher.matches(aString, this.getTestPattern(patternString, caseSensitive));
    }


    /**
     * Returns true if the compiled version of the patternString regular
     * expression argument is contained in the aString argument.
     *
     * @param aString a string
     * @param patternString a pattern string
     * @return Returns true if the compiled version of the patternString regular expression argument is contained in the aString argument
     * @throws MalformedPatternException
     */
    public boolean contains(String aString, String patternString) throws MalformedPatternException {
        return this.matcher.contains(aString, this.getTestPattern(patternString));
    }

    /**
     * Compiles and caches a case sensitive regexp pattern for the given string pattern.
     *
     * @param stringPattern a pattern string
     * @return compiles and caches a case sensitive regexp pattern for the given string pattern
     * @throws MalformedPatternException
     */
    private Pattern getTestPattern(String stringPattern) throws MalformedPatternException {
        return this.getTestPattern(stringPattern, true);
    }

    /**
     * Compiles and caches a regexp pattern for the given string pattern.
     *
     * @param stringPattern a pattern string
     * @param caseSensitive case sensitive true/false
     * @return compiles and caches a regexp pattern for the given string pattern
     * @throws MalformedPatternException
     */
    private Pattern getTestPattern(String stringPattern, boolean caseSensitive) throws MalformedPatternException {
        Pattern pattern = compiledPatterns.get(stringPattern);
        if (pattern == null) {
            if (caseSensitive) {
                pattern = compiler.compile(stringPattern);
            } else {
                pattern = compiler.compile(stringPattern, Perl5Compiler.CASE_INSENSITIVE_MASK);
            }

            compiledPatterns.put(stringPattern, pattern);
            Debug.logVerbose("Compiled and cached a pattern: '" + stringPattern + "' - " + Thread.currentThread(), module);
        }
        return pattern;
    }

    /**
     * Perl5Util's substitute() function implements Perl's s/// operator.
     * It takes two arguments: a substitution expression, and an input.
     *
     * @param stringPattern a pattern string
     * @param input the input string
     * @return returns the perl5Util's substitute() function implements Perl's
     */
    public String substitute(String stringPattern, String input) {
        return this.perl5Util.substitute(stringPattern, input);
    }
}
