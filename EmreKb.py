import ccxt
import pandas as pd

def ema(df, n):
    EMA = pd.Series(df['close'].ewm(span=n, adjust=False).mean(), name='EMA_' + str(n))
    df = df.join(EMA)
    return df

def macd(df, n_fast, n_slow):
    EMAfast = pd.Series(df['close'].ewm(span=n_fast, adjust=False).mean(), name='EMAfast')
    EMAslow = pd.Series(df['close'].ewm(span=n_slow, adjust=False).mean(), name='EMAslow')
    MACD = pd.Series(EMAfast - EMAslow, name='MACD_' + str(n_fast) + '_' + str(n_slow))
    MACDsign = pd.Series(MACD.ewm(span=9, adjust=False).mean(), name='MACDsign_' + str(n_fast) + '_' + str(n_slow))
    MACDdiff = pd.Series(MACD - MACDsign, name='MACDdiff_' + str(n_fast) + '_' + str(n_slow))
    df = df.join(MACD)
    df = df.join(MACDsign)
    df = df.join(MACDdiff)
    return df

def stochastic_oscillator(df):
    L14 = pd.Series(df['low'].rolling(window=14).min(), name='L14')
    H14 = pd.Series(df['high'].rolling(window=14).max(), name='H14')
    K = pd.Series((df['close'] - L14) / (H14 - L14), name='%K')
    df = df.join(K)
    D = pd.Series(K.ewm(span=3, adjust=False).mean(), name='%D')
    df = df.join(D)
    return df

def rsi(df, n):
    delta = df['close'].diff()
    gain = delta.where(delta > 0, 0)
    loss = abs(delta.where(delta < 0, 0))
    avg_gain = gain.rolling(window=n).mean()
    avg_loss = loss.rolling(window=n).mean()
    rs = avg_gain / avg_loss
    RSI = 100 - (100 / (1 + rs))
    RSI = pd.Series(RSI, name='RSI_' + str(n))
    df = df.join(RSI)
    return df

def bollinger_bands(df, window, width):
    rolling_mean = df['close'].rolling(window=window).mean()
    rolling_std = df['close'].rolling(window=window).std()
    upper_band = rolling_mean + (rolling_std * width)
    upper_band = pd.Series(upper_band, name='UpperBand_' + str(window))
