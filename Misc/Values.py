#!/usr/bin/env python2.7
# -*- coding: utf-8 -*-
from argparse import ArgumentParser as ap
from os import system as s
class Application():
    def __init__(self):
        source = ap(description='¡Genera los valores exactos para algunas particiones!')
        nope = source.add_mutually_exclusive_group()
        source.add_argument('M', type=int, help="Tamaño disponible")
        source.add_argument('-X', type=float, help="1° Porcentaje a calcular", nargs='?', const=16, default=16)
        source.add_argument('-Y', type=float, help="2° Porcentaje a calcular", nargs='?', const=84, default=84)
        source.add_argument('-A', type=float, help="3° Porcentaje a calcular", nargs='?', const=32, default=32)
        source.add_argument('-B', type=float, help="4° Porcentaje a calcular", nargs='?', const=68, default=68)
        source.add_argument('-R', type=int, help="Memoria RAM", nargs='?', const=0, default=0)
        source.add_argument('-E', type=int, help="Tamaño de la partición EFI", nargs='?', const=261, default=261)
        source.add_argument('-L', type=int, help="Espacio vacío al final del disco", nargs='?', const=1, default=1)
        nope.add_argument('-MB', help="Tipo de Unidad para medir el tamaño disponible", action="store_true")
        nope.add_argument('-GB', help="Tipo de Unidad para medir el tamaño disponible", action="store_true")
        Values = self.Values()
        s("clear")
        print Values.linux(source.parse_args(), self.Funcs)
        print Values.windows(source.parse_args(), self.Funcs)
    class Funcs():
		class Rounder():
			def __init__(self):
				self.limit = 10
				self.max= 5
			def round(self, a, dec=0):
				var = list(self.modifier(float(a)))
				self.modifier2(var, dec)
				self.modifier3(var)
				if dec:
					return float(str(str().join(str(e) for e in var)))
				else:
					return int(float(str(str().join(str(e) for e in var))))
			def modifier(self, a):
				for i in str(a):
					try:
						yield int(i)
					except ValueError:
						yield str(i)
			def modifier2(self, a, max=int(), negative=-1):
				int_counter, decimal_counter = (0, 0)
				if max <= 0:
					max = 0
				for i in a:
					if i != '.':
						int_counter += 1
					else:
						break
				for i in reversed(a):
					if i != '.':
						decimal_counter += 1
					else:
						break
				for i in reversed(a):
					if len(a) == int_counter+1:
						break
					elif i == ".":
						pass
					elif i < self.max:
						if max:
							if int_counter+max+1 >= len(a):
								break
						a.pop(-1)
					elif i >= self.max:
						if max:
							if int_counter+max+1 >= len(a):
								break
						a.pop(-1)
						while True:
							try:
								a[negative] += 1
								break
							except TypeError:
								negative -= 1
			def modifier3(self, a, negative=1, l=False, n=False):
				if not l:
					l = {}
				if not n:
					n = {}
				for j, i in enumerate(reversed(a), start=1):
					if len(l) and len(n):
						try:
							a[(n.values()[-1]-negative)] += l.values()[-1]
							n.clear()
							l.clear()
							for b, c in enumerate(reversed(a), start=1):
								if c is self.limit:
									if b is not len(a):
										self.modifier3(a)
							return
						except TypeError:
							negative += 1
					else:
						if i == ".":
							pass
						elif j is len(a):
							pass
						elif i >= self.limit:
							l[j] = 1
							n[j] = a.index(i)
							a[a.index(i)] = 0
						elif i < self.limit:
							l[j] = 0
    class Values():
        def linux(self, args, f):
            Functions = f()
            Rounder = Functions.Rounder()
            Round = Rounder.round
            if args.MB:
                U = float(1)
            elif args.GB:
                U = float(1024)
            else:
                U = float(1024)
            verify1 = args.X+args.Y
            verify2 = args.A+args.B
            verify3 = args.E+Round(args.R*1.64)+args.L+2
            if verify1 != 100:
                if verify1 < 100:
                    check1 = 100-verify1
                    args.X += check1/2
                    args.Y += check1/2
                elif verify1 > 100:
                    check1 = verify1-100
                    args.X -= check1/2
                    args.Y -= check1/2
            if verify2 != 100:
                if verify2 < 100:
                    check2 = 100-verify2
                    args.A += check2/2
                    args.B += check2/2
                elif verify2 > 100:
                    check2 = verify2-100
                    args.A -= check2/2
                    args.B -= check2/2
            if verify3 > args.M*U:
                raise Exception("%s%s" % ("No space left to assign. Minimum: ", str(float(args.E)+float(args.L)+2).rstrip()))
            T = str(float(float(args.M)*U)).rstrip()
            R = str(float(float(args.R)*U)).rstrip()
            S = str(Round(float(float(float(R)*1.64)))).rstrip()
            E = str(float(float(args.E))).rstrip()
            M = str(float(float(float(T)-(float(S)+float(E))))).rstrip()
            L = str(float(float(args.L))).rstrip()
            X = str(float(Round((float(args.X)*0.01)*float(M)))).rstrip()
            Y = str(float(Round(((float(args.Y)*0.01)*float(M))-float(L)))).rstrip()
            XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
            A = str(float(Round((float(args.A)*0.01)*float(M)))).rstrip()
            B = str(float(Round(((float(args.B)*0.01)*float(M))-float(L)))).rstrip()
            AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
            while float(XY) != float(T):
                if float(XY) > float(T):
                    result = float(float(XY) - float(T))
                    Y = str(float(float(Y)-result))
                    XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
                    if float(XY) == float(T):
                        break
                elif float(XY) < float(T):
                    result = float(float(T) - float(XY))
                    Y = str(float(float(Y)+result))
                    XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
                    if float(XY) == float(T):
                        break
            while float(AB) != float(T):
                if float(AB) > float(T):
                    result = float(float(AB) - float(T))
                    B = str(float(float(B)-result))
                    AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
                    if float(AB) == float(T):
                        break
                elif float(AB) < float(T):
                    result = float(float(T) - float(AB))
                    B = str(float(float(B)+result))
                    AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
                    if float(AB) == float(T):
                        break
            return ("## Linux ##\n\n%s\n%s\n\n%s\n%s\n\n%s\n\n%s\n%s\n\n%s\n\n%s\n%s\n%s" % (str(int(float(T))).rstrip(),str(int(float(M))).rstrip(),int(float(X)),int(float(Y)),int(float(XY)),int(float(A)),int(float(B)),int(float(AB)),int(float(S)),int(float(E)),str(int(int(float(L)))).rstrip()))
        def windows(self, args, f):
            Functions = f()
            Rounder = Functions.Rounder()
            Round = Rounder.round
            if args.MB:
                U = float(1)
            elif args.GB:
                U = float(1024)
            else:
                U = float(1024)
            T = str(float(float(args.M)*U)).rstrip()
            R = 0
            S = 0
            E = str(float(float(args.E))).rstrip()
            M = str(float(float(float(T)-(float(S)+float(E))))).rstrip()
            L = str(float(float(args.L))).rstrip()
            X = str(float(Round((float(args.X)*0.01)*float(M)))).rstrip()
            Y = str(float(Round(((float(args.Y)*0.01)*float(M))-float(L)))).rstrip()
            XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
            A = str(float(Round((float(args.A)*0.01)*float(M)))).rstrip()
            B = str(float(Round(((float(args.B)*0.01)*float(M))-float(L)))).rstrip()
            AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
            while float(XY) != float(T):
                if float(XY) > float(T):
                    result = float(float(XY) - float(T))
                    Y = str(float(float(Y)-result))
                    XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
                    if float(XY) == float(T):
                        break
                elif float(XY) < float(T):
                    result = float(float(T) - float(XY))
                    Y = str(float(float(Y)+result))
                    XY = str(float(float(X)+(float(Y)+float(L))+float(S)+float(E))).rstrip()
                    if float(XY) == float(T):
                        break
            while float(AB) != float(T):
                if float(AB) > float(T):
                    result = float(float(AB) - float(T))
                    B = str(float(float(B)-result))
                    AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
                    if float(AB) == float(T):
                        break
                elif float(AB) < float(T):
                    result = float(float(T) - float(AB))
                    B = str(float(float(B)+result))
                    AB = str(float(float(A)+(float(B)+float(L))+float(S)+float(E))).rstrip()
                    if float(AB) == float(T):
                        break
            if float(T) == float(M):
                return ("## Windows ##\n\n%s\n\n%s\n%s\n\n%s\n\n%s\n%s\n\n%s" % (str(int(float(T))).rstrip(),int(float(X)),int(float(Y)),int(float(XY)),int(float(A)),int(float(B)),int(float(AB))))
            else:
                return ("## Windows ##\n\n%s\n%s\n\n%s\n%s\n\n%s\n\n%s\n%s\n\n%s\n\n%s\n%s" % (str(int(int(float(T)))).rstrip(),str(int(int(float(M)))).rstrip(),int(float(X)),int(float(Y)),int(float(XY)),int(float(A)),int(float(B)),int(float(AB)),int(int(float(E))),str(int(int(int(float(L))))).rstrip()))
if __name__ == '__main__':
    Application()
